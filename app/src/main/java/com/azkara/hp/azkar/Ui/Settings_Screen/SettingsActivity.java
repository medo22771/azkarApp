package com.azkara.hp.azkar.Ui.Settings_Screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Util.GeneralMethods;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_settings);
    }
}
