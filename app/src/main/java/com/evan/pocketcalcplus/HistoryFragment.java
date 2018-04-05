package com.evan.pocketcalcplus;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history,container,false);

        rootView.setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));

        ListView historyListView = rootView.findViewById(R.id.listHistory);
        historyListView.setAdapter(((MainActivity)getActivity()).history);

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();

        // Set the background color.
        this.getView().setBackgroundColor(SettingsActivity.getBackgroundColor(this.getActivity()));
    }

}
