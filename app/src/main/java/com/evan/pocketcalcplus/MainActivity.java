package com.evan.pocketcalcplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // The following code creates all the buttons but does not initialize them
    private Button mZero_Button, mOne_Button, mTwo_Button, mThree_Button, mFour_Button, mFive_Button, mSix_Button,
            mSeven_Button, mEight_Button, mNine_Button, mSub_Button, mMult_Button,
            mDiv_Button, mDel_Button, mClear_Button, mModulo_Button, mAdd_Button;
    // This creats the EditText for the calculator screen
    private EditText mCalculatorScreen;



// dr calculeto lives
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mZero_Button = (Button) findViewById(R.id.Zero_Button);
        mOne_Button = (Button) findViewById(R.id.One_Button);
        mTwo_Button = (Button) findViewById(R.id.Two_Button);
        mThree_Button = (Button) findViewById(R.id.Three_Button);
        mFour_Button = (Button) findViewById(R.id.Four_Button);
        mFive_Button = (Button) findViewById(R.id.Five_Button);
        mSix_Button = (Button) findViewById(R.id.Six_Button);
        mSeven_Button = (Button) findViewById(R.id.Seven_Button);
        mEight_Button = (Button) findViewById(R.id.Eight_Button);
        mNine_Button = (Button) findViewById(R.id.Nine_Button);
        mSub_Button = (Button) findViewById(R.id.Sub_Button);
        mMult_Button = (Button) findViewById(R.id.Mult_Button);
        mDiv_Button = (Button) findViewById(R.id.Div_Button);
        mDel_Button = (Button) findViewById(R.id.Clear_Button);
        mClear_Button = (Button) findViewById(R.id.Del_Button);
        mModulo_Button = (Button) findViewById(R.id.Modulo_Button);
        mAdd_Button = (Button) findViewById(R.id.Add_Button);
//        input = (TextView)findViewById(R.id.tvInput);
//        result = (TextView)findViewById(R.id.tvResult);
        mCalculatorScreen = (EditText) findViewById(R.id.Calculator_Screen);
        

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
                mCalculatorScreen.setText(mTwo_Button.getText().toString());
            }
        });

        mThree_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mThree_Button.getText().toString() + "3");
            }
        });

        mFour_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mFour_Button.getText().toString() + "4");
            }
        });

        mFive_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mFive_Button.getText().toString() + "5");
            }
        });

        mSix_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mSix_Button.getText().toString() + "6");
            }
        });

        mSeven_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mSeven_Button.getText().toString() + "7");
            }
        });

        mEight_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mEight_Button.getText().toString() + "8");
            }
        });

        mNine_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mNine_Button.getText().toString() + "9");
            }
        });

        mSub_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mSub_Button.getText().toString() + "-");
            }
        });

        mMult_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mMult_Button.getText().toString() + "*");
            }
        });

        mDiv_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mDiv_Button.getText().toString() + "/");
            }
        });

        mDel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mDel_Button.getText().toString());
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
                mCalculatorScreen.setText(mModulo_Button.getText().toString());
            }
        });

        mAdd_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalculatorScreen.setText(mAdd_Button.getText().toString());
            }
        });

    }





}
