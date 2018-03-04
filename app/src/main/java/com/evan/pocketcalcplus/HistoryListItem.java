package com.evan.pocketcalcplus;

public class HistoryListItem {
    private String data; // The raw data in the HistoryListItem (before prettifier).
    private int source; // The source to go to when pressed.
    private boolean answer; // Is it an answer? If so, right align.

    HistoryListItem(String data, int source, boolean answer) {
        this.data = data;
        this.source = source;
        this.answer = answer;
    }

    public String getData() {
        return data;
    }

    public String getPrettyData() {
        return EquationPrettifier.prettifyInput(getData());
    }

    int getSource() {
        return source;
    }

    boolean getAnswer() {
        return answer;
    }
}
