package com.evan.pocketcalcplus;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    //TODO Replace with single "preferences_theme" and switch {case} in each getColor method.
    private static final String preferences_background_color = "background_color";
    private static final String preferences_primary_color = "primary_color";
    private static final String preferences_accent_color = "accent_color";
    private static final String preferences_text_color = "accent_color";

    public static int getBackgroundColor(Activity activity) {
        // This method handles getting the current background color,
        // for the sake of abstraction and code elegance.
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        String background_color = sharedPref.getString(preferences_background_color, "#6b6b6b");
        return Color.parseColor(background_color);
    }

}
