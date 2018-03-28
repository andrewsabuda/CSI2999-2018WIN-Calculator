package com.evan.pocketcalcplus;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    public static final String bc = "background_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String background_color = sharedPref.getString(SettingsActivity.bc, "#6b6b6b");
        Toast.makeText(this, background_color.toString(), Toast.LENGTH_SHORT).show();
    }
}
