package com.evan.pocketcalcplus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.myscript.iink.ContentBlock;
import com.myscript.iink.ContentPart;
import com.myscript.iink.Editor;
import com.myscript.iink.Engine;
import com.myscript.iink.IEditorListener;
import com.myscript.iink.MimeType;
import com.myscript.iink.uireferenceimplementation.EditorView;
import com.myscript.iink.uireferenceimplementation.IInputControllerListener;
import com.myscript.iink.uireferenceimplementation.ImageLoader;
import com.myscript.iink.uireferenceimplementation.InputController;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class DrawingFragment extends Fragment implements View.OnClickListener {

    protected EditorView editorView;

    protected DocumentController documentController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawing, container, false);

        rootView.setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));

        /*
        Engine engine = MyScriptEngine.getEngine();
        MyScriptEngine.configureEngine(engine, getContext().getPackageCodePath(),
                getContext().getFilesDir().getPath());

        editorView = rootView.findViewById(R.id.editor_view);
        editorView.setEngine(engine);

        final Editor editor = editorView.getEditor();
        editor.addListener(new IEditorListener() {
            @Override
            public void partChanging(Editor editor, ContentPart oldPart, ContentPart newPart) {
                // no-op
            }

            @Override
            public void partChanged(Editor editor) {
                getActivity().invalidateOptionsMenu();
                invalidateIconButtons();
            }

            @Override
            public void contentChanged(Editor editor, String[] blockIds) {
                getActivity().invalidateOptionsMenu();
                invalidateIconButtons();
            }

            @Override
            public void onError(Editor editor, String blockId, String message) {
                Log.e("DrawingFragment", "Failed to edit block \"" + blockId + "\"" + message);
            }
        });

        editorView.setImageLoader(new ImageLoader(editor, this.getContext().getCacheDir()));

        InputController inputController = new InputController(this.getContext(), editorView);
        editorView.setInputController(inputController);
        setInputMode(InputController.INPUT_MODE_FORCE_PEN); // If using an active pen, put INPUT_MODE_AUTO here

        inputController.setListener(new IInputControllerListener() {
            @Override
            public void onDisplayContextMenu(final float x, final float y, final ContentBlock contentBlock, final String[] supportedAddBlockTypes) {
                displayContextMenu(x, y, contentBlock, supportedAddBlockTypes);
            }
        });

        documentController = new DocumentController(getActivity(), editorView);
        final String fileName = documentController.getSavedFileName();
        final int partIndex = documentController.getSavedPartIndex();

        // wait for view size initialization before setting part
        editorView.post(new Runnable() {
            @Override
            public void run() {
                if (fileName != null)
                    documentController.openPart(fileName, partIndex);
                else
                    documentController.newPart();
            }
        });

        rootView.findViewById(R.id.button_input_mode_forcePen).setOnClickListener(this);
        rootView.findViewById(R.id.button_input_mode_forceTouch).setOnClickListener(this);
        rootView.findViewById(R.id.button_input_mode_auto).setOnClickListener(this);
        rootView.findViewById(R.id.button_undo).setOnClickListener(this);
        rootView.findViewById(R.id.button_redo).setOnClickListener(this);
        rootView.findViewById(R.id.button_clear).setOnClickListener(this);

        invalidateIconButtons();
        */

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Set the background color.
        this.getView().setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));
    }

    private boolean addImage(final float x, final float y) {
        final File[] files = getActivity().getFilesDir().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return (name.endsWith(".gif") || name.endsWith(".png")
                        || name.endsWith(".svg") || name.endsWith(".jpg")
                        || name.endsWith(".jpeg") || name.endsWith(".jpe"));
            }
        });

        if (files.length == 0) {
            Log.e("DrawingFragment", "Failed to add image, image list is empty");
            return false;
        }

        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; ++i)
            fileNames[i] = files[i].getName();

        final int[] selected = new int[]{0};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle(R.string.addImage_title);

        dialogBuilder.setSingleChoiceItems(fileNames, selected[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selected[0] = which;
            }
        });

        dialogBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    File file = files[selected[0]];

                    if (file != null) {
                        MimeType mimeType = null;

                        for (MimeType mimeType_ : MimeType.values()) {
                            if (!mimeType_.isImage())
                                continue;

                            String fileExtensions = mimeType_.getFileExtensions();

                            if (fileExtensions == null)
                                continue;

                            String[] extensions = fileExtensions.split(" *, *");

                            for (int i = 0; i < extensions.length; ++i) {
                                if (file.getName().endsWith(extensions[i])) {
                                    mimeType = mimeType_;
                                    break;
                                }
                            }
                        }

                        if (mimeType != null) {
                            editorView.getEditor().addImage(x, y, file, mimeType);
                        }
                    }
                } catch (Exception e) {
                    Log.e("DrawingFragment", "Failed to add image", e);
                }
            }
        });

        dialogBuilder.setNegativeButton(R.string.cancel, null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        return true;
    }

    private void displayContextMenu(final float x, final float y, ContentBlock contentBlock_, final String[] supportedAddBlockTypes) {
        final Editor editor = editorView.getEditor();
        final ContentBlock contentBlock = contentBlock_ != null ? contentBlock_ : editor.getRootBlock();

        final boolean isContainer = contentBlock.getType().equals("Container");
        final boolean isRoot = contentBlock.getId().equals(editor.getRootBlock().getId());

        final boolean displayConvert = !isContainer;
        final boolean displayAddBlock = supportedAddBlockTypes.length > 0 && isContainer;
        final boolean displayAddImage = false; // supportedAddBlockTypes.length > 0 && isContainer;
        final boolean displayRemove = !isRoot && !isContainer;
        final boolean displayCopy = !isRoot && !isContainer;
        final boolean displayPaste = supportedAddBlockTypes.length > 0 && isContainer;
        final boolean displayImport = false;
        final boolean displayExport = false;

        final ArrayList<String> items = new ArrayList<>();

        if (displayConvert)
            items.add("Convert");

        if (displayAddBlock) {
            for (String blockType : supportedAddBlockTypes) {
                // filter out "Text" block, until we have a popup menu to ask for text data to import
                if (blockType.equals("Text"))
                    continue;
                items.add("Add " + blockType);
            }
        }

        if (displayAddImage)
            items.add("Add Image");

        if (displayPaste)
            items.add("Paste");

        if (displayRemove)
            items.add("Remove");

        if (displayCopy)
            items.add("Copy");

        if (displayImport)
            items.add("Import");

        if (displayExport)
            items.add("Export");

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle(contentBlock.getType() + " (id: " + contentBlock.getId() + ")");
        dialogBuilder.setItems(items.toArray(new String[items.size()]), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String item = items.get(which);

                    if (item.equals("Convert")) {
                        editor.convert(contentBlock, editor.getSupportedTargetConversionStates(contentBlock)[0]);
                    } else if (item.equals("Remove")) {
                        editor.removeBlock(contentBlock);
                    } else if (item.equals("Copy")) {
                        editor.copy(contentBlock);
                    } else if (item.equals("Paste")) {
                        editor.paste(x, y);
                    } else if (item.equals("Import")) {
                        // TODO
                    } else if (item.equals("Export")) {
                        // TODO
                    } else if (item.equals("Add Image")) {
                        addImage(x, y);
                    } else if (item.startsWith("Add ")) {
                        String blockType = item.substring(4);
                        editor.addBlock(x, y, blockType);
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Operation failed : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });
        dialogBuilder.show();
    }

    private void setInputMode(int inputMode) {
        editorView.setInputMode(inputMode);
        getActivity().findViewById(R.id.button_input_mode_forcePen).setEnabled(inputMode != InputController.INPUT_MODE_FORCE_PEN);
        getActivity().findViewById(R.id.button_input_mode_forceTouch).setEnabled(inputMode != InputController.INPUT_MODE_FORCE_TOUCH);
        getActivity().findViewById(R.id.button_input_mode_auto).setEnabled(inputMode != InputController.INPUT_MODE_AUTO);
    }

    private void invalidateIconButtons() {
        Editor editor = editorView.getEditor();
        final boolean canUndo = editor.canUndo();
        final boolean canRedo = editor.canRedo();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageButton imageButtonUndo = getActivity().findViewById(R.id.button_undo);
                imageButtonUndo.setEnabled(canUndo);
                ImageButton imageButtonRedo = getActivity().findViewById(R.id.button_redo);
                imageButtonRedo.setEnabled(canRedo);
                ImageButton imageButtonClear = getActivity().findViewById(R.id.button_clear);
                imageButtonClear.setEnabled(documentController != null && documentController.hasPart());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_input_mode_forcePen:
                setInputMode(InputController.INPUT_MODE_FORCE_PEN);
                break;
            case R.id.button_input_mode_forceTouch:
                setInputMode(InputController.INPUT_MODE_FORCE_TOUCH);
                break;
            case R.id.button_input_mode_auto:
                setInputMode(InputController.INPUT_MODE_AUTO);
                break;
            case R.id.button_undo:
                editorView.getEditor().undo();
                break;
            case R.id.button_redo:
                editorView.getEditor().redo();
                break;
            case R.id.button_clear:
                editorView.getEditor().clear();
                break;
            default:
                Log.e("DrawingFragment", "Failed to handle click event");
                break;
        }
    }

}