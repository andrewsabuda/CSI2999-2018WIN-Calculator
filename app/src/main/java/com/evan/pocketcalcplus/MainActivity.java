package com.evan.pocketcalcplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mZero_Button, mOne_Button, mTwo_Button, mThree_Button, mFour_Button, mFive_Button, mSix_Button,
            mSeven_Button, mEight_Button, mNine_Button, mSub_Button, mMult_Button,
            mDiv_Button, mDel_Button, mClear_Button, mModulo_Button, mAdd_Button;
    private TextView input, result;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char MODULUS = '%';
    private double val1 = Double.NaN;
    private double val2;


// dr calculeto lives
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();

        mZero_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "0");
            }
        });

        mOne_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "1");
            }
        });

        mTwo_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "2");
            }
        });

        mThree_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "3");
            }
        });

        mFour_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "4");
            }
        });

        mFive_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "5");
            }
        });

        mSix_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "6");
            }
        });

        mSeven_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "7");
            }
        });

        mEight_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "8");
            }
        });

        mNine_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "9");
            }
        });

        mSub_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "-");
            }
        });

        mMult_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "*");
            }
        });

        mDiv_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "/");
            }
        });

        mDel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "_");
            }
        });

        mClear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "clear");
            }
        });

        mModulo_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "%");
            }
        });

        mAdd_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString() + "+");
            }
        });

    }

    private void setupUIViews(){

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
        input = (TextView)findViewById(R.id.tvInput);
        result = (TextView)findViewById(R.id.tvResult);


    }
}
