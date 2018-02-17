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

public class MainActivity extends AppCompatActivity {
    // The following code creates all the buttons but does not initialize them
    private Button mZero_Button, mOne_Button, mTwo_Button, mThree_Button, mFour_Button, mFive_Button, mSix_Button,
            mSeven_Button, mEight_Button, mNine_Button, mSub_Button, mMult_Button,
            mDiv_Button, mDel_Button, mClear_Button, mModulo_Button, mAdd_Button, mDot_Button, mEquals_Button ;
    // This creates the EditText for the calculator screen
    private EditText mCalculatorScreen;

//test by clay

// dr calculeto lives
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The following code initializes the buttons and finds them by their xml ids
        mCalculatorScreen = (EditText) findViewById(R.id.editTextCalculatorScreen);

        mZero_Button = (Button) findViewById(R.id.buttonZero);
        mOne_Button = (Button) findViewById(R.id.buttonOne);
        mTwo_Button = (Button) findViewById(R.id.buttonTwo);
        mThree_Button = (Button) findViewById(R.id.buttonThree);
        mFour_Button = (Button) findViewById(R.id.buttonFour);
        mFive_Button = (Button) findViewById(R.id.buttonFive);
        mSix_Button = (Button) findViewById(R.id.buttonSix);
        mSeven_Button = (Button) findViewById(R.id.buttonSeven);
        mEight_Button = (Button) findViewById(R.id.buttonEight);
        mNine_Button = (Button) findViewById(R.id.buttonNine);
        mAdd_Button = (Button) findViewById(R.id.buttonAdd);
        mSub_Button = (Button) findViewById(R.id.buttonSubtract);
        mMult_Button = (Button) findViewById(R.id.buttonMultiply);
        mDiv_Button = (Button) findViewById(R.id.buttonDivide);
        mModulo_Button = (Button) findViewById(R.id.buttonModulo);
        mDel_Button = (Button) findViewById(R.id.buttonDelete);
        mClear_Button = (Button) findViewById(R.id.buttonClear);
        mDot_Button = (Button) findViewById(R.id.buttonDecimalPoint);
        mEquals_Button = (Button) findViewById(R.id.Equals_Button);

        mCalculatorScreen.setInputType(InputType.TYPE_NULL);
        mCalculatorScreen.setTextIsSelectable(true);

        mZero_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mZero_Button.getText().toString());
            }
        });

        mOne_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mOne_Button.getText().toString());
            }
        });

        mTwo_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mTwo_Button.getText().toString());
            }
        });

        mThree_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mThree_Button.getText().toString());
            }
        });

        mFour_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mFour_Button.getText().toString());
            }
        });

        mFive_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mFive_Button.getText().toString());
            }
        });

        mSix_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mSix_Button.getText().toString());
            }
        });

        mSeven_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mSeven_Button.getText().toString());
            }
        });

        mEight_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mEight_Button.getText().toString());
            }
        });

        mNine_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mNine_Button.getText().toString());
            }
        });

        mSub_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mSub_Button.getText().toString());
            }
        });

        mMult_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mMult_Button.getText().toString());
            }
        });

        mDiv_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mDiv_Button.getText().toString());
            }
        });

        mDel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = mCalculatorScreen.getText().length();
                if (length > 0)
                    mCalculatorScreen.setTextKeepState(mCalculatorScreen.getText().delete(length-1,length));
            }
        });

        mClear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText("");
            }
        });

        mModulo_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mModulo_Button.getText().toString());
            }
        });

        mAdd_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mAdd_Button.getText().toString());
            }
        });

        mDot_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.append(mDot_Button.getText().toString());
            }
        });

        mEquals_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCalculatorScreen.getText().toString().length() > 0) {
                    String prefix = RegexParser.inputToPrefix(mCalculatorScreen.getText().toString());
                    double answer = RegexParser.parsePrefix(prefix);
                    String answerString = String.valueOf(RegexParser.parsePrefix(prefix));
                    if(answer % 1 == 0 && answerString.contains("E") == false) {
                        mCalculatorScreen.setTextKeepState(answerString.substring(0,answerString.length() - 2));
                    }
                    else {
                        mCalculatorScreen.setTextKeepState(answerString);
                    }
                }
            }
        });
    }





}
