package com.azkara.hp.azkar.Ui.Splash_Screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Util.GeneralMethods;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_splash_screen);
    }
}
