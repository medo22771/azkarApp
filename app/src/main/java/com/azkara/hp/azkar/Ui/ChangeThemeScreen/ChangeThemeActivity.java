package com.azkara.hp.azkar.Ui.ChangeThemeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Ui.Home_Screen.HomeScreenActivity;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;

public class ChangeThemeActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btnSave;
    private ImageView btnBack;
    private AppCompatRadioButton radioStyle1, radioStyle2, radioStyle3, radioStyle4;
    private SharedPrefManager prefManager;
    private int selectedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        selectedTheme = prefManager.getThemeColor();
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_change_theme);
        initViews();
        selectCurrentTheme();
    }


    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        radioStyle1 = findViewById(R.id.radioStyle1);
        radioStyle2 = findViewById(R.id.radioStyle2);
        radioStyle3 = findViewById(R.id.radioStyle3);
        radioStyle4 = findViewById(R.id.radioStyle4);

        btnBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        radioStyle1.setOnClickListener(this);
        radioStyle2.setOnClickListener(this);
        radioStyle3.setOnClickListener(this);
        radioStyle4.setOnClickListener(this);
    }

    private void selectCurrentTheme() {
        if (selectedTheme == Constants.ConstantsValues.LightTheme) {
            radioStyle1.setChecked(true);
        } else if (selectedTheme == Constants.ConstantsValues.DarkTheme) {
            radioStyle2.setChecked(true);
        } else if (selectedTheme == Constants.ConstantsValues.GreenTheme) {
            radioStyle3.setChecked(true);
        } else if (selectedTheme == Constants.ConstantsValues.PinkTheme) {
            radioStyle4.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioStyle1:
                selectedTheme = Constants.ConstantsValues.LightTheme;
                break;
            case R.id.radioStyle2:
                selectedTheme = Constants.ConstantsValues.DarkTheme;
                break;
            case R.id.radioStyle3:
                selectedTheme = Constants.ConstantsValues.GreenTheme;
                break;
            case R.id.radioStyle4:
                selectedTheme = Constants.ConstantsValues.PinkTheme;
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnSave:
                prefManager.setThemeColor(selectedTheme);
                startActivity(new Intent(ChangeThemeActivity.this, HomeScreenActivity.class));
                finish();
                break;
        }
    }
}
