package com.evan.pocketcalcplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // This creates the EditText for the calculator screen
    private EditText editTextCalculatorScreen;

    // This stores the current input to be displayed or prettified.
    private String currentInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The following code initializes the buttons and finds them by their xml ids
        editTextCalculatorScreen = findViewById(R.id.editTextCalculatorScreen);

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

        editTextCalculatorScreen.setInputType(InputType.TYPE_NULL);
        editTextCalculatorScreen.setTextIsSelectable(true);
    }

    public String prettifyInput(String input) {
        return input;
    }

    public String parseEquation(String input) {
        String prefix = RegexParser.inputToPrefix(input);
        double answer = RegexParser.parsePrefix(prefix);
        String answerString = String.valueOf(RegexParser.parsePrefix(prefix));
        if(answer % 1 == 0 && !answerString.contains("E")) {
            return answerString.substring(0,answerString.length() - 2);
        }
        else {
            return answerString;
        }
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
                break;
        }
        editTextCalculatorScreen.setTextKeepState(prettifyInput(currentInput));
    }

}
