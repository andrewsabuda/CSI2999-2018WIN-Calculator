package com.evan.pocketcalcplus;

import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public HistoryListAdapter history;

    // This stores the current input to be displayed or prettified.
    public String currentInput = "";

    private TogglableViewPager viewPager;

    public void toggleViewPager(boolean enabled) {
        // Set whether the view pager can swipe between tabs or not.
        // Disable swiping on drawing tab.
        viewPager.setPagingEnabled(enabled);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");
        android.support.v7.preference.PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        history = new HistoryListAdapter(this, new ArrayList<>());

        // Set up the ViewPager with the sections adapter.
        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons(tabLayout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }

        // Fix which ensures onResume is always called.
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            FragmentManager manager = getSupportFragmentManager();
            if (manager != null) {
                int backStackEntryCount = manager.getBackStackEntryCount();
                if (backStackEntryCount == 0) {
                    finish();
                }
                Fragment fragment = manager.getFragments().get(backStackEntryCount - 1);
                fragment.onResume();
            }
        });
    }

    //This creates the options menu, which currently contains the settings button
    //xml file can be found at res->menu->main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //This starts an activity when a button is pressed on the options menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //handle presses on the option menu
        switch (item.getItemId()) {

            case R.id.settings_id:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(TAB_SIMPLE, new SimpleFragment(), "");
        adapter.addFragment(TAB_SCIENTIFIC, new ScientificFragment(), "");
        adapter.addFragment(TAB_HISTORY, new HistoryFragment(), "");
        adapter.addFragment(TAB_DRAWING, new DrawingFragment(), "");
        adapter.addFragment(TAB_VOICE, new VoiceFragment(), "");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(TAB_SIMPLE).setIcon(R.drawable.ic_tab_simple);
        tabLayout.getTabAt(TAB_SCIENTIFIC).setIcon(R.drawable.ic_tab_scientific);
        tabLayout.getTabAt(TAB_HISTORY).setIcon(R.drawable.ic_tab_history);
        tabLayout.getTabAt(TAB_DRAWING).setIcon(R.drawable.ic_tab_drawing);
        tabLayout.getTabAt(TAB_VOICE).setIcon(R.drawable.ic_tab_voice);
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
    // Alarms that activate after a certain time.
    static final int TAB_DRAWING = 3;
    // Alarms that activate after a certain time.
    static final int TAB_VOICE = 4;
    // The number of tabs.
    static final int COUNT_TABS = 5;

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