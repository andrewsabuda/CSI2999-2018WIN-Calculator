package com.evan.pocketcalcplus;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryListAdapter extends BaseAdapter {
    private MainActivity context;
    private ArrayList<HistoryListItem> history;

    HistoryListAdapter(MainActivity context, ArrayList<HistoryListItem> history) {
        this.context = context;
        this.history = history;
    }

    @Override
    public int getCount() {
        return history.size();
    }

    @Override
    public Object getItem(int position) {
        return history.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(HistoryListItem item) {
        history.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Turn an ArrayList item into a History button.
        View vi = convertView;
        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.fragment_history_list_item, parent, false);
        }
        HistoryListItem item = history.get(position);
        vi.setTag(item);

        TextView itemText = vi.findViewById(R.id.itemText);
        // Setting alignment (answer on right side) requires a certain API level.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            itemText.setTextAlignment(item.getAnswer() ? View.TEXT_ALIGNMENT_VIEW_END : View.TEXT_ALIGNMENT_VIEW_START);
        }
        itemText.setText(item.getPrettyData());

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the history list item is clicked...
                HistoryListItem item = (HistoryListItem) view.getTag();
                context.currentInput = item.getData();
                context.setCurrentTab(item.getSource());
            }
        });

        return vi;
    }

}
