package com.evan.pocketcalcplus;

import android.content.ActivityNotFoundException;
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

import static android.app.Activity.RESULT_OK;

public class VoiceFragment extends Fragment implements View.OnClickListener {

    private EditText editTextCalculatorScreen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_voice, container, false);

        editTextCalculatorScreen = view.findViewById(R.id.editTextCalculatorScreenVoice);

        view.findViewById(R.id.btnSpeak).setOnClickListener(this);

        editTextCalculatorScreen.setInputType(InputType.TYPE_NULL);
        editTextCalculatorScreen.setTextIsSelectable(true);
        editTextCalculatorScreen.setTextKeepState(prettifyInput(((MainActivity) getActivity()).currentInput));

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("VoiceFragment", "setUserVisibleHint");
        if (isVisibleToUser) {
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

    @Override
    public void onClick(View view) {
        // Run when a button is pressed.
        MainActivity main = (MainActivity) getActivity();
        if (main == null) return; // This shouldn't happen, it just helps in case of errors.

        switch (view.getId()) {
            case R.id.btnSpeak:
                promptSpeechInput();
                break;
        }
    }

    static final int CALC_SPEECH_INPUT = 100;

    //This method is run when the speech button is clicked.
    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say calculations");

        try {
            startActivityForResult(intent, CALC_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(VoiceFragment.this.getContext(), "Device does not support speech", Toast.LENGTH_LONG).show();
        }

    }

    public void onActivityResult(int request_code, int result_code, Intent intent) {
        super.onActivityResult(request_code, result_code, intent);

        switch (request_code) {
            case CALC_SPEECH_INPUT:
                if (result_code == RESULT_OK && intent != null) {
                    ArrayList<String> result = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ((MainActivity) getActivity()).currentInput = result.get(0);
                }
                break;
        }
        editTextCalculatorScreen.setTextKeepState(prettifyInput(((MainActivity) getActivity()).currentInput));
    }
}
