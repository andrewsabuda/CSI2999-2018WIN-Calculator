package com.evan.pocketcalcplus;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.graphics.Color;

import java.util.Set;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class SimpleFragment extends Fragment implements View.OnClickListener {
    // This creates the EditText for the calculator screen
    private EditText editTextCalculatorScreen;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple,container,false);

        view.setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));
        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(SettingsActivity.getTabColor(this.getActivity()));
        setOperationButtonColor(view, SettingsActivity.getOperationColor(this.getActivity()));
        setNumberButtonColor(view, SettingsActivity.getNumberColor(this.getActivity()));
        ((MainActivity) getActivity()).getSupportActionBar()
                .setBackgroundDrawable(new ColorDrawable(SettingsActivity.getHeaderColor(this.getActivity())));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(SettingsActivity.getHeaderColor(this.getActivity()));
        }

        // The following code initializes the buttons and finds them by their xml ids
        editTextCalculatorScreen = view.findViewById(R.id.editTextCalculatorScreen);
        editTextCalculatorScreen.setTextColor(SettingsActivity.getTextColor(this.getActivity()));

        view.findViewById(R.id.buttonZero).setOnClickListener(this);
        view.findViewById(R.id.buttonOne).setOnClickListener(this);
        view.findViewById(R.id.buttonTwo).setOnClickListener(this);
        view.findViewById(R.id.buttonThree).setOnClickListener(this);
        view.findViewById(R.id.buttonFour).setOnClickListener(this);
        view.findViewById(R.id.buttonFive).setOnClickListener(this);
        view.findViewById(R.id.buttonSix).setOnClickListener(this);
        view.findViewById(R.id.buttonSeven).setOnClickListener(this);
        view.findViewById(R.id.buttonEight).setOnClickListener(this);
        view.findViewById(R.id.buttonNine).setOnClickListener(this);
        view.findViewById(R.id.buttonAdd).setOnClickListener(this);
        view.findViewById(R.id.buttonSubtract).setOnClickListener(this);
        view.findViewById(R.id.buttonMultiply).setOnClickListener(this);
        view.findViewById(R.id.buttonDivide).setOnClickListener(this);
        view.findViewById(R.id.buttonModulo).setOnClickListener(this);
        view.findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        view.findViewById(R.id.buttonDelete).setOnClickListener(this);
        view.findViewById(R.id.buttonClear).setOnClickListener(this);
        view.findViewById(R.id.buttonEquals).setOnClickListener(this);
        //view.findViewById(R.id.buttonHistory).setOnClickListener(this);


        editTextCalculatorScreen.setInputType(InputType.TYPE_NULL);
        editTextCalculatorScreen.setTextIsSelectable(true);
        editTextCalculatorScreen.setTextKeepState(prettifyInput(((MainActivity) getActivity()).currentInput));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the text.
        editTextCalculatorScreen.setTextKeepState(prettifyInput(((MainActivity) getActivity()).currentInput));
        // Set the background color.
        this.getView().setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));
        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(SettingsActivity.getTabColor(this.getActivity()));
        setOperationButtonColor(this.getView(), SettingsActivity.getOperationColor(this.getActivity()));
        setNumberButtonColor(this.getView(), SettingsActivity.getNumberColor(this.getActivity()));
        ((MainActivity) getActivity()).getSupportActionBar()
                .setBackgroundDrawable(new ColorDrawable(SettingsActivity.getHeaderColor(this.getActivity())));
    }

    public void setOperationButtonColor(View rootView, int color) {
        rootView.findViewById(R.id.buttonAdd).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSubtract).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonMultiply).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonDivide).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonModulo).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonClear).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonDelete).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonDecimalPoint).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonEquals).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonParenthesis).setBackgroundColor(color);
    }

    public void setNumberButtonColor(View rootView, int color) {
        rootView.findViewById(R.id.buttonOne).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonTwo).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonThree).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonFour).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonFive).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSix).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSeven).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonEight).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonNine).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonZero).setBackgroundColor(color);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("SimpleFragment", "setUserVisibleHint");
        if(isVisibleToUser) {
            MainActivity main = ((MainActivity) getActivity());
            if (main != null) {
                Log.i("SimpleFragment", main.currentInput);
                editTextCalculatorScreen.setTextKeepState(prettifyInput(((MainActivity) getActivity()).currentInput));
            }
        }
    }

    public String prettifyInput(String input) {
        return EquationPrettifier.prettifyInput(input);
    }

    public String parseEquation(String input) {
        return RegexParser.parseEquation(input);
    }

    @Override
    public void onClick(View view) {
        // Run when a button is pressed.
        MainActivity main = (MainActivity) getActivity();
        if (main == null) return; // This shouldn't happen, it just helps in case of errors.

        switch(view.getId()) {
            case R.id.buttonZero:
                main.currentInput += "0"; break;
            case R.id.buttonOne:
                main.currentInput += "1"; break;
            case R.id.buttonTwo:
                main.currentInput += "2"; break;
            case R.id.buttonThree:
                main.currentInput += "3"; break;
            case R.id.buttonFour:
                main.currentInput += "4"; break;
            case R.id.buttonFive:
                main.currentInput += "5"; break;
            case R.id.buttonSix:
                main.currentInput += "6"; break;
            case R.id.buttonSeven:
                main.currentInput += "7"; break;
            case R.id.buttonEight:
                main.currentInput += "8"; break;
            case R.id.buttonNine:
                main.currentInput += "9"; break;
            case R.id.buttonAdd:
                main.currentInput += "+"; break;
            case R.id.buttonSubtract:
                main.currentInput += "-"; break;
            case R.id.buttonMultiply:
                main.currentInput += "*"; break;
            case R.id.buttonDivide:
                main.currentInput += "/"; break;
            case R.id.buttonModulo:
                main.currentInput += "%"; break;
            case R.id.buttonDecimalPoint:
                main.currentInput += "."; break;
            case R.id.buttonDelete:
                int length = main.currentInput.length();
                if (length > 0)
                    main.currentInput = main.currentInput.substring(0, length - 1);
                break;
            case R.id.buttonClear:
                main.currentInput = "";
                break;
            case R.id.buttonEquals:
                // Add expression to history.
                main.history.add(new HistoryListItem(main.currentInput, MainActivity.TAB_SIMPLE, false));
                // Calculate answer.
                main.currentInput = parseEquation(main.currentInput);
                // Add answer to history.
                main.history.add(new HistoryListItem(main.currentInput, MainActivity.TAB_SIMPLE, true));
                break;

        }

        editTextCalculatorScreen.setTextKeepState(prettifyInput(main.currentInput));
    }

}
