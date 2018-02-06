package com.evan.pocketcalcplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mZero_Button, mOne_Button, mTwo_Button, mThree_Button, mFour_Button, mFive_Button, mSix_Button,
            mSeven_Button, mEight_Button, mNine_Button, mSub_Button, mMult_Button,
            mDiv_Button, mDel_Button, mClear_Button, mModulo_Button, mAdd_Button;



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


    }
}
