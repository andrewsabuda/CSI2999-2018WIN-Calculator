package com.evan.pocketcalcplus;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;
import java.util.ArrayList;

public class VoiceFragment extends Fragment {

    private EditText editTextCalculatorScreen;
    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice,container,false);

        editTextCalculatorScreen = view.findViewById(R.id.editTextCalculatorScreenVoice);
        relativeLayout = view.findViewById(R.id.relativeVoice);

        editTextCalculatorScreen.setInputType(InputType.TYPE_NULL);
        editTextCalculatorScreen.setTextIsSelectable(true);
        editTextCalculatorScreen.setTextKeepState(prettifyInput(((MainActivity) getActivity()).currentInput));

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("VoiceFragment", "setUserVisibleHint");
        if(isVisibleToUser) {
            MainActivity main = ((MainActivity) getActivity());
            if (main != null) {
                Log.i("VoiceFragment", main.currentInput);
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

}
