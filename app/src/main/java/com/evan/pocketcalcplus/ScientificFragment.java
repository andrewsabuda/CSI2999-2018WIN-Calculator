package com.evan.pocketcalcplus;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ScientificFragment extends Fragment implements View.OnClickListener {


    private EditText calculatorScreen;

    private String currentInput = "";

    SetInterface history = new ArraySet();

    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scientific,container,false);

        calculatorScreen = view.findViewById(R.id.editTextCalculatorScreen);

        relativeLayout = view.findViewById(R.id.relative);

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
        view.findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        view.findViewById(R.id.buttonDelete).setOnClickListener(this);
        view.findViewById(R.id.buttonDivide).setOnClickListener(this);
        view.findViewById(R.id.buttonMultiply).setOnClickListener(this);
        view.findViewById(R.id.buttonModulo).setOnClickListener(this);
        view.findViewById(R.id.buttonAdd).setOnClickListener(this);
        view.findViewById(R.id.buttonSubtract).setOnClickListener(this);
        view.findViewById(R.id.buttonSquared).setOnClickListener(this);
        view.findViewById(R.id.buttonTangent).setOnClickListener(this);
        view.findViewById(R.id.buttonSine).setOnClickListener(this);
        view.findViewById(R.id.buttonCosine).setOnClickListener(this);
        view.findViewById(R.id.buttonLn).setOnClickListener(this);
        view.findViewById(R.id.buttonExponent).setOnClickListener(this);
        view.findViewById(R.id.buttonFactorial).setOnClickListener(this);
        view.findViewById(R.id.buttonPi).setOnClickListener(this);
        view.findViewById(R.id.buttonParentheses).setOnClickListener(this);
        view.findViewById(R.id.buttonClear).setOnClickListener(this);

        calculatorScreen.setInputType(InputType.TYPE_NULL);
        calculatorScreen.setTextIsSelectable(true);

        return view;
    }

    public String prettifyInput(String input){
        return EquationPrettifier.prettifyInput(input);
    }

    public String parseEquation(String input){
        return RegexParser.parseEquation(input);
    }




}
