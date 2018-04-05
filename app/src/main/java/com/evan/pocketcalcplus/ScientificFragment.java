package com.evan.pocketcalcplus;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import android.preference.PreferenceManager;

public class ScientificFragment extends Fragment implements View.OnClickListener {
    private EditText editTextCalculatorScreen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scientific,container,false);

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
        editTextCalculatorScreen = view.findViewById(R.id.editTextCalculatorScreenSci);
        editTextCalculatorScreen.setTextColor(SettingsActivity.getTextColor(this.getActivity()));

        view.findViewById(R.id.buttonZeroSci).setOnClickListener(this);
        view.findViewById(R.id.buttonOneSci).setOnClickListener(this);
        view.findViewById(R.id.buttonTwoSci).setOnClickListener(this);
        view.findViewById(R.id.buttonThreeSci).setOnClickListener(this);
        view.findViewById(R.id.buttonFourSci).setOnClickListener(this);
        view.findViewById(R.id.buttonFiveSci).setOnClickListener(this);
        view.findViewById(R.id.buttonSixSci).setOnClickListener(this);
        view.findViewById(R.id.buttonSevenSci).setOnClickListener(this);
        view.findViewById(R.id.buttonEightSci).setOnClickListener(this);
        view.findViewById(R.id.buttonNineSci).setOnClickListener(this);
        view.findViewById(R.id.buttonDecimalPointSci).setOnClickListener(this);
        view.findViewById(R.id.buttonDeleteSci).setOnClickListener(this);
        view.findViewById(R.id.buttonDivideSci).setOnClickListener(this);
        view.findViewById(R.id.buttonMultiplySci).setOnClickListener(this);
        view.findViewById(R.id.buttonModuloSci).setOnClickListener(this);
        view.findViewById(R.id.buttonAddSci).setOnClickListener(this);
        view.findViewById(R.id.buttonSubtractSci).setOnClickListener(this);
        view.findViewById(R.id.buttonSquaredSci).setOnClickListener(this);
        view.findViewById(R.id.buttonTangentSci).setOnClickListener(this);
        view.findViewById(R.id.buttonSineSci).setOnClickListener(this);
        view.findViewById(R.id.buttonCosineSci).setOnClickListener(this);
        view.findViewById(R.id.buttonLnSci).setOnClickListener(this);
        view.findViewById(R.id.buttonExponentSci).setOnClickListener(this);
        view.findViewById(R.id.buttonFactorialSci).setOnClickListener(this);
        view.findViewById(R.id.buttonPiSci).setOnClickListener(this);
        view.findViewById(R.id.buttonParenthesesSci).setOnClickListener(this);
        view.findViewById(R.id.buttonClearSci).setOnClickListener(this);
        view.findViewById(R.id.buttonEqualsSci).setOnClickListener(this);

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
        rootView.findViewById(R.id.buttonAddSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSubtractSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonMultiplySci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonDivideSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonModuloSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonClearSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonDeleteSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonDecimalPointSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonEqualsSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonParenthesesSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSineSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonCosineSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonTangentSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonPiSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonLnSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSquaredSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonExponentSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonFactorialSci).setBackgroundColor(color);
    }

    public void setNumberButtonColor(View rootView, int color) {
        rootView.findViewById(R.id.buttonOneSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonTwoSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonThreeSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonFourSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonFiveSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSixSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonSevenSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonEightSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonNineSci).setBackgroundColor(color);
        rootView.findViewById(R.id.buttonZeroSci).setBackgroundColor(color);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("ScientificFragment", "setUserVisibleHint");
        if(isVisibleToUser) {
            MainActivity main = ((MainActivity) getActivity());
            if (main != null) {
                Log.i("ScientificFragment", main.currentInput);
                editTextCalculatorScreen.setTextKeepState(prettifyInput(((MainActivity) getActivity()).currentInput));
            }
        }
    }

    public String prettifyInput(String input){
        return EquationPrettifier.prettifyInput(input);
    }

    public String parseEquation(String input){
        return RegexParser.parseEquation(input);
    }

    @Override
    public void onClick(View view) {
        // Run when a button is pressed.
        MainActivity main = (MainActivity) getActivity();
        if (main == null) return; // This shouldn't happen, it just helps in case of errors.

        switch(view.getId()) {
            case R.id.buttonZeroSci:
                main.currentInput += "0"; break;
            case R.id.buttonOneSci:
                main.currentInput += "1"; break;
            case R.id.buttonTwoSci:
                main.currentInput += "2"; break;
            case R.id.buttonThreeSci:
                main.currentInput += "3"; break;
            case R.id.buttonFourSci:
                main.currentInput += "4"; break;
            case R.id.buttonFiveSci:
                main.currentInput += "5"; break;
            case R.id.buttonSixSci:
                main.currentInput += "6"; break;
            case R.id.buttonSevenSci:
                main.currentInput += "7"; break;
            case R.id.buttonEightSci:
                main.currentInput += "8"; break;
            case R.id.buttonNineSci:
                main.currentInput += "9"; break;
            case R.id.buttonAddSci:
                main.currentInput += "+"; break;
            case R.id.buttonSubtractSci:
                main.currentInput += "-"; break;
            case R.id.buttonMultiplySci:
                main.currentInput += "*"; break;
            case R.id.buttonDivideSci:
                main.currentInput += "/"; break;
            case R.id.buttonModuloSci:
                main.currentInput += "%"; break;
            case R.id.buttonDecimalPointSci:
                main.currentInput += "."; break;
            case R.id.buttonDeleteSci:
                int length = main.currentInput.length();
                if (length > 0)
                    main.currentInput = main.currentInput.substring(0, length - 1);
                break;
            case R.id.buttonClearSci:
                main.currentInput = "";
                break;
            case R.id.buttonEqualsSci:
                // Add expression to history.
                main.history.add(new HistoryListItem(main.currentInput, MainActivity.TAB_SCIENTIFIC, false));
                // Calculate answer.
                main.currentInput = parseEquation(main.currentInput);
                // Add answer to history.
                main.history.add(new HistoryListItem(main.currentInput, MainActivity.TAB_SCIENTIFIC, true));
                break;
                // scientific fragment specific buttons below!!
            case R.id.buttonSquaredSci:
                main.currentInput += " ^ 2"; break;
            case R.id.buttonTangentSci:
                main.currentInput += RegexParser.OPERATOR_TAN; break;
            case R.id.buttonSineSci:
                main.currentInput += RegexParser.OPERATOR_SIN; break;
            case R.id.buttonCosineSci:
                main.currentInput += RegexParser.OPERATOR_COS; break;
            case R.id.buttonLnSci:
                main.currentInput += RegexParser.OPERATOR_LOG; break;
            case R.id.buttonExponentSci:
                main.currentInput += "^"; break;
            //case R.id.buttonFactorialSci:
                //main.currentInput += "!"; break;
            case R.id.buttonPiSci:
                main.currentInput += RegexParser.OPERATOR_PI; break;
            case R.id.buttonParenthesesSci:
                main.currentInput += "("; break;
        }

        editTextCalculatorScreen.setTextKeepState(prettifyInput(main.currentInput));
    }

}
