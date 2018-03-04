package com.evan.pocketcalcplus;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HistoryFragment extends Fragment {

    private TextView textHist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history,container,false);

        textHist = view.findViewById(R.id.textHist);

        textHist.setText("testing testing testing testing testing testing");
        return view;
    }
}
