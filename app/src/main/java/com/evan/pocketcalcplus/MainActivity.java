package com.evan.pocketcalcplus;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public HistoryListAdapter history;

    // This stores the current input to be displayed or prettified.
    public String currentInput = "";

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        history = new HistoryListAdapter(this, new ArrayList<HistoryListItem>());

        // Set up the ViewPager with the sections adapter.
        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(TAB_SIMPLE, new SimpleFragment(), "Simple");
        adapter.addFragment(TAB_SCIENTIFIC, new ScientificFragment(), "Scientific");
        adapter.addFragment(TAB_HISTORY, new HistoryFragment(), "History");
        viewPager.setAdapter(adapter);
    }

    public void setCurrentTab(int tab) {
        viewPager.setCurrentItem(tab);
    }

    // The launch screen of the application.
    static final int TAB_SIMPLE = 0;
    // Alarms that activate on a regular basis.
    static final int TAB_SCIENTIFIC = 1;
    // Alarms that activate after a certain time.
    static final int TAB_HISTORY = 2;
    // The number of tabs.
    static final int COUNT_TABS = 3;

    public class SectionsPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        void addFragment(int id, Fragment fragment, String title) {
            mFragmentList.add(id, fragment);
            mFragmentTitleList.add(id, title);
        }

        SectionsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

}