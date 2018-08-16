package com.azkara.hp.azkar.Ui.Home_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.azkara.hp.azkar.Model.Azkary;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Ui.Azkar_Muslem_Screen.AzkarElMoslemActivity;
import com.azkara.hp.azkar.Ui.Azkary_Screen.AzkaryActivity;
import com.azkara.hp.azkar.Ui.Sebha_Screen.SebhaActivity;
import com.azkara.hp.azkar.Ui.Settings_Screen.SettingsActivity;
import com.azkara.hp.azkar.Ui.Werd_El_Mo7asba_Screen.WerdMohasbaActivity;
import com.azkara.hp.azkar.Util.Constants;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rlAzkary, rlAzkarElMoslem, rlSettings, rlWerdElMohasba, rlSebha;
    RadioButton radioVeryHigh, radioHigh, radioMedium, radioLow;
    SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        initViews();
        checkAlarmSettings();
    }

    private void initViews() {
        rlAzkarElMoslem = findViewById(R.id.rlAzkarElMoslem);
        rlAzkary = findViewById(R.id.rlAzkary);
        rlWerdElMohasba = findViewById(R.id.rlWerdMohasba);
        rlSettings = findViewById(R.id.rlSettings);
        rlSebha = findViewById(R.id.rlSebha);

        radioVeryHigh = findViewById(R.id.radioVeryHigh);
        radioHigh = findViewById(R.id.radioHigh);
        radioMedium = findViewById(R.id.radioMedium);
        radioLow = findViewById(R.id.radioLow);

        rlAzkarElMoslem.setOnClickListener(this);
        rlAzkary.setOnClickListener(this);
        rlSettings.setOnClickListener(this);
        rlWerdElMohasba.setOnClickListener(this);
        rlSebha.setOnClickListener(this);
        radioVeryHigh.setOnClickListener(this);
        radioHigh.setOnClickListener(this);
        radioMedium.setOnClickListener(this);
        radioLow.setOnClickListener(this);
    }

    private void checkAlarmSettings() {
        long repeatValue = prefManager.getAlarmManagerRepeatTime();
        if (repeatValue == Constants.ConstantsValues.VeryHighRepeat) {
            radioVeryHigh.setChecked(true);
        } else if (repeatValue == Constants.ConstantsValues.HighRepeat) {
            radioHigh.setChecked(true);
        } else if (repeatValue == Constants.ConstantsValues.MediumRepeat) {
            radioMedium.setChecked(true);
        } else if (repeatValue == Constants.ConstantsValues.LowRepeat) {
            radioLow.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radioVeryHigh:
                radioVeryHigh.setChecked(true);
                radioHigh.setChecked(false);
                radioMedium.setChecked(false);
                radioLow.setChecked(false);
                break;

            case R.id.radioHigh:
                radioVeryHigh.setChecked(false);
                radioHigh.setChecked(true);
                radioMedium.setChecked(false);
                radioLow.setChecked(false);
                break;

            case R.id.radioMedium:
                radioVeryHigh.setChecked(false);
                radioHigh.setChecked(false);
                radioMedium.setChecked(true);
                radioLow.setChecked(false);
                break;

            case R.id.radioLow:
                radioVeryHigh.setChecked(false);
                radioHigh.setChecked(false);
                radioMedium.setChecked(false);
                radioLow.setChecked(true);
                break;

            case R.id.rlAzkarElMoslem:
                startActivity(new Intent(HomeScreenActivity.this, AzkarElMoslemActivity.class));
                break;

            case R.id.rlAzkary:
                startActivity(new Intent(HomeScreenActivity.this, AzkaryActivity.class));
                break;
            case R.id.rlWerdMohasba:
                startActivity(new Intent(HomeScreenActivity.this, WerdMohasbaActivity.class));
                break;
            case R.id.rlSettings:
                startActivity(new Intent(HomeScreenActivity.this, SettingsActivity.class));
                break;
            case R.id.rlSebha:
                startActivity(new Intent(HomeScreenActivity.this, SebhaActivity.class));
                break;

        }
    }
}
//        AllAzkarReprosatory reprosatory = new AllAzkarReprosatory(getApplication());
//        reprosatory.getAllAzkarElMoslem().observe(this, new Observer<List<AzkarElMoslem>>() {
//            @Override
//            public void onChanged(@Nullable List<AzkarElMoslem> azkarElMoslems) {
//                for (AzkarElMoslem azkarElMoslem : azkarElMoslems){
//                    Log.e("nader",azkarElMoslem.getZekrContent());
//                }
//            }
//        });
