package com.evan.pocketcalcplus;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class SettingsActivity extends AppCompatActivity implements Preference.OnPreferenceChangeListener {
    public static final String THEME = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
        updateColors();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        updateColors();
        return true;
    }

    public void updateColors() {
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(SettingsActivity.getHeaderColor(this)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(SettingsActivity.getHeaderColor(this));
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public static int getBackgroundColor(Activity activity) {
        // This method handles getting the current background color,
        // for the sake of abstraction and code elegance.
        switch (PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(SettingsActivity.THEME, "stream")) {
            case "stream":
                return activity.getResources().getColor(R.color.theme_background_stream);
            case "dracula":
                return activity.getResources().getColor(R.color.theme_background_dracula);
            case "junebug":
                return activity.getResources().getColor(R.color.theme_background_junebug);
            case "sunshine":
                return activity.getResources().getColor(R.color.theme_background_sunshine);
            case "kitten":
                return activity.getResources().getColor(R.color.theme_background_kitten);
            case "forest":
                return activity.getResources().getColor(R.color.theme_background_forest);
            default:
                return activity.getResources().getColor(R.color.backgroundLight);
        }
    }

    public static int getTextColor(Activity activity) {
        // This method handles getting the current background color,
        // for the sake of abstraction and code elegance.
        switch (PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(SettingsActivity.THEME, "stream")) {
            case "stream":
                return activity.getResources().getColor(R.color.theme_text_stream);
            case "dracula":
                return activity.getResources().getColor(R.color.theme_text_dracula);
            case "junebug":
                return activity.getResources().getColor(R.color.theme_text_junebug);
            case "sunshine":
                return activity.getResources().getColor(R.color.theme_text_sunshine);
            case "kitten":
                return activity.getResources().getColor(R.color.theme_text_kitten);
            case "forest":
                return activity.getResources().getColor(R.color.theme_text_forest);
            default:
                return activity.getResources().getColor(R.color.textDark);
        }
    }

    public static int getNumberColor(Activity activity) {
        // This method handles getting the current background color,
        // for the sake of abstraction and code elegance.
        switch (PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(SettingsActivity.THEME, "stream")) {
            case "stream":
                return activity.getResources().getColor(R.color.theme_numbers_stream);
            case "dracula":
                return activity.getResources().getColor(R.color.theme_numbers_dracula);
            case "junebug":
                return activity.getResources().getColor(R.color.theme_numbers_junebug);
            case "sunshine":
                return activity.getResources().getColor(R.color.theme_numbers_sunshine);
            case "kitten":
                return activity.getResources().getColor(R.color.theme_numbers_kitten);
            case "forest":
                return activity.getResources().getColor(R.color.theme_numbers_forest);
            default:
                return activity.getResources().getColor(R.color.backgroundDark);
        }
    }

    public static int getOperationColor(Activity activity) {
        // This method handles getting the current background color,
        // for the sake of abstraction and code elegance.
        switch (PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(SettingsActivity.THEME, "stream")) {
            case "stream":
                return activity.getResources().getColor(R.color.theme_operations_stream);
            case "dracula":
                return activity.getResources().getColor(R.color.theme_operations_dracula);
            case "junebug":
                return activity.getResources().getColor(R.color.theme_operations_junebug);
            case "sunshine":
                return activity.getResources().getColor(R.color.theme_operations_sunshine);
            case "kitten":
                return activity.getResources().getColor(R.color.theme_operations_kitten);
            case "forest":
                return activity.getResources().getColor(R.color.theme_operations_forest);
            default:
                return activity.getResources().getColor(R.color.backgroundDark);
        }
    }

    public static int getHeaderColor(Activity activity) {
        // This method handles getting the current background color,
        // for the sake of abstraction and code elegance.
        switch (PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(SettingsActivity.THEME, "stream")) {
            case "stream":
                return activity.getResources().getColor(R.color.theme_header_stream);
            case "dracula":
                return activity.getResources().getColor(R.color.theme_header_dracula);
            case "junebug":
                return activity.getResources().getColor(R.color.theme_header_junebug);
            case "sunshine":
                return activity.getResources().getColor(R.color.theme_header_sunshine);
            case "kitten":
                return activity.getResources().getColor(R.color.theme_header_kitten);
            case "forest":
                return activity.getResources().getColor(R.color.theme_header_forest);
            default:
                return activity.getResources().getColor(R.color.backgroundDark);
        }
    }

    public static int getTabColor(Activity activity) {
        // This method handles getting the current background color,
        // for the sake of abstraction and code elegance.
        switch (PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(SettingsActivity.THEME, "stream")) {
            case "stream":
                return activity.getResources().getColor(R.color.theme_tablist_stream);
            case "dracula":
                return activity.getResources().getColor(R.color.theme_tablist_dracula);
            case "junebug":
                return activity.getResources().getColor(R.color.theme_tablist_junebug);
            case "sunshine":
                return activity.getResources().getColor(R.color.theme_tablist_sunshine);
            case "kitten":
                return activity.getResources().getColor(R.color.theme_tablist_kitten);
            case "forest":
                return activity.getResources().getColor(R.color.theme_tablist_forest);
            default:
                return activity.getResources().getColor(R.color.backgroundDark);
        }
    }
}
