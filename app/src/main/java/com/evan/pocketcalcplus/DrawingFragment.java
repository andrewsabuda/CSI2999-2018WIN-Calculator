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
import java.util.Objects;

public class DrawingFragment extends Fragment implements View.OnClickListener {

    protected EditorView editorView;

    protected DocumentController documentController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawing, container, false);

        rootView.setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));

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
                invalidateIconButtons(getView());
            }

            @Override
            public void contentChanged(Editor editor, String[] blockIds) {
                getActivity().invalidateOptionsMenu();
                invalidateIconButtons(getView());
            }

            @Override
            public void onError(Editor editor, String blockId, String message) {
                Log.e("DrawingFragment", "Failed to edit block \"" + blockId + "\"" + message);
            }
        });

        editorView.setImageLoader(new ImageLoader(editor, this.getContext().getCacheDir()));

        InputController inputController = new InputController(this.getContext(), editorView);
        editorView.setInputController(inputController);
        setInputMode(rootView, InputController.INPUT_MODE_FORCE_PEN); // If using an active pen, put INPUT_MODE_AUTO here

        documentController = new DocumentController(getActivity(), editorView);
        final String fileName = documentController.getSavedFileName();
        final int partIndex = documentController.getSavedPartIndex();

        // wait for view size initialization before setting part
        editorView.post(() -> {
            if (fileName != null)
                documentController.openPart(fileName, partIndex);
            else
                documentController.newMathPart();
        });

        rootView.findViewById(R.id.button_input_mode_forcePen).setOnClickListener(this);
        rootView.findViewById(R.id.button_input_mode_forceTouch).setOnClickListener(this);
        rootView.findViewById(R.id.button_input_mode_auto).setOnClickListener(this);
        rootView.findViewById(R.id.button_undo).setOnClickListener(this);
        rootView.findViewById(R.id.button_redo).setOnClickListener(this);
        rootView.findViewById(R.id.button_clear).setOnClickListener(this);
        rootView.findViewById(R.id.button_convert).setOnClickListener(this);

        invalidateIconButtons(rootView);

        setOperationButtonColor(rootView, SettingsActivity.getOperationColor(this.getActivity()));

        return rootView;
    }

    public void setOperationButtonColor(View rootView, int color) {
        rootView.findViewById(R.id.button_input_mode_auto).setBackgroundColor(color);
        rootView.findViewById(R.id.button_input_mode_forcePen).setBackgroundColor(color);
        rootView.findViewById(R.id.button_input_mode_forceTouch).setBackgroundColor(color);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Set the background color.
        this.getView().setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));

        setOperationButtonColor(this.getView(), SettingsActivity.getOperationColor(this.getActivity()));
        documentController.loadState();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //documentController.saveToTemp();
        if (documentController != null) documentController.saveToTemp();
    }

    private void setInputMode(View rootView, int inputMode) {
        editorView.setInputMode(inputMode);
        try {
            rootView.findViewById(R.id.button_input_mode_forcePen).setEnabled(inputMode != InputController.INPUT_MODE_FORCE_PEN);
            rootView.findViewById(R.id.button_input_mode_forceTouch).setEnabled(inputMode != InputController.INPUT_MODE_FORCE_TOUCH);
            rootView.findViewById(R.id.button_input_mode_auto).setEnabled(inputMode != InputController.INPUT_MODE_AUTO);
        } catch (NullPointerException e) {
            Log.w("DrawingFragment", "NullPointerException setting input mode.");
        }
    }

    private void invalidateIconButtons(View rootView) {
        Editor editor = editorView.getEditor();
        final boolean canUndo = editor.canUndo();
        final boolean canRedo = editor.canRedo();
        getActivity().runOnUiThread(() -> {
            ImageButton imageButtonUndo = rootView.findViewById(R.id.button_undo);
            imageButtonUndo.setEnabled(canUndo);
            ImageButton imageButtonRedo = rootView.findViewById(R.id.button_redo);
            imageButtonRedo.setEnabled(canRedo);
            ImageButton imageButtonClear = rootView.findViewById(R.id.button_clear);
            imageButtonClear.setEnabled(documentController != null && documentController.hasPart());
        });
    }

    @Override
    public void onClick(View v) {
        Editor editor = editorView.getEditor();
        switch (v.getId()) {
            case R.id.button_input_mode_forcePen:
                setInputMode(getView(), InputController.INPUT_MODE_FORCE_PEN);
                break;
            case R.id.button_input_mode_forceTouch:
                setInputMode(getView(), InputController.INPUT_MODE_FORCE_TOUCH);
                break;
            case R.id.button_input_mode_auto:
                setInputMode(getView(), InputController.INPUT_MODE_AUTO);
                break;
            case R.id.button_undo:
                editor.undo();
                break;
            case R.id.button_redo:
                editor.redo();
                break;
            case R.id.button_clear:
                editor.clear();
                break;
            case R.id.button_convert:
                editor.convert(null, editor.getSupportedTargetConversionStates(null)[0]);
            default:
                Log.e("DrawingFragment", "Failed to handle click event");
                break;
        }
    }

}