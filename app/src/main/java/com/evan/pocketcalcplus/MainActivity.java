package com.evan.pocketcalcplus;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // This creates the EditText for the calculator screen
    private EditText editTextCalculatorScreen;

    // This stores the current input to be displayed or prettified.
    private String currentInput = "";

    // History interface
    SetInterface history = new ArraySet();

    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The following code initializes the buttons and finds them by their xml ids
        editTextCalculatorScreen = findViewById(R.id.editTextCalculatorScreen);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        findViewById(R.id.buttonZero).setOnClickListener(this);
        findViewById(R.id.buttonOne).setOnClickListener(this);
        findViewById(R.id.buttonTwo).setOnClickListener(this);
        findViewById(R.id.buttonThree).setOnClickListener(this);
        findViewById(R.id.buttonFour).setOnClickListener(this);
        findViewById(R.id.buttonFive).setOnClickListener(this);
        findViewById(R.id.buttonSix).setOnClickListener(this);
        findViewById(R.id.buttonSeven).setOnClickListener(this);
        findViewById(R.id.buttonEight).setOnClickListener(this);
        findViewById(R.id.buttonNine).setOnClickListener(this);
        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonModulo).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);
        findViewById(R.id.buttonDelete).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonHistory).setOnClickListener(this);

        editTextCalculatorScreen.setInputType(InputType.TYPE_NULL);
        editTextCalculatorScreen.setTextIsSelectable(true);

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
        switch(view.getId()) {
            case R.id.buttonZero:
                currentInput += "0"; break;
            case R.id.buttonOne:
                currentInput += "1"; break;
            case R.id.buttonTwo:
                currentInput += "2"; break;
            case R.id.buttonThree:
                currentInput += "3"; break;
            case R.id.buttonFour:
                currentInput += "4"; break;
            case R.id.buttonFive:
                currentInput += "5"; break;
            case R.id.buttonSix:
                currentInput += "6"; break;
            case R.id.buttonSeven:
                currentInput += "7"; break;
            case R.id.buttonEight:
                currentInput += "8"; break;
            case R.id.buttonNine:
                currentInput += "9"; break;
            case R.id.buttonAdd:
                currentInput += "+"; break;
            case R.id.buttonSubtract:
                currentInput += "-"; break;
            case R.id.buttonMultiply:
                currentInput += "*"; break;
            case R.id.buttonDivide:
                currentInput += "/"; break;
            case R.id.buttonModulo:
                currentInput += "%"; break;
            case R.id.buttonDecimalPoint:
                currentInput += "."; break;
            case R.id.buttonDelete:
                int length = currentInput.length();
                if (length > 0)
                    currentInput = currentInput.substring(0, length - 1);
                break;
            case R.id.buttonClear:
                currentInput = "";
                break;
            case R.id.buttonEquals:
                currentInput = parseEquation(currentInput);
        //When equals button is clicked, adds currentInput to the history
                history.add(String.valueOf(currentInput));
                break;
            case R.id.buttonHistory:
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.history_popup,null);

                popupWindow = new PopupWindow(container,800,1000,true);
                popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,50,1500);

                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });

                    break;
        }

        editTextCalculatorScreen.setTextKeepState(prettifyInput(currentInput));
    }

}
