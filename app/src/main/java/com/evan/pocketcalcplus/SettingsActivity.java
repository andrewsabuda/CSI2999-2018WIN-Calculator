package com.evan.pocketcalcplus;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    public static final String bc = "background_color";
    public static final String tc = "text_Color";
    public static final String nc = "number_Color";
    public static final String oc = "operation_Color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String background_color = sharedPref.getString(SettingsActivity.bc, "#6b6b6b");
        String text_Color = sharedPref.getString(SettingsActivity.tc, "#000000");
        String number_Color = sharedPref.getString(SettingsActivity.nc, "D0CCC8");
        String operation_Color = sharedPref.getString(SettingsActivity.oc, "FFBB44444");
        Toast.makeText(this, background_color.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, number_Color.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this, operation_Color.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this, text_Color.toString(),Toast.LENGTH_SHORT).show();
    }
}
