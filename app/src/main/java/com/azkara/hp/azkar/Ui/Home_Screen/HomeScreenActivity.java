package com.azkara.hp.azkar.Ui.Home_Screen;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Ui.Azkar_Muslem_Screen.AzkarElMoslemActivity;
import com.azkara.hp.azkar.Ui.Azkary_Screen.AzkaryActivity;
import com.azkara.hp.azkar.Ui.Settings_Screen.SettingsActivity;
import com.azkara.hp.azkar.Ui.Werd_El_Mo7asba_Screen.WerdMohasbaActivity;
import com.azkara.hp.azkar.Ui.sebhaAzkarScreen.SebhaAzkarActivity;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rlAzkary, rlAzkarElMoslem, rlSettings, rlWerdElMohasba, rlSebha;
    private RadioButton radioVeryHigh, radioHigh, radioMedium, radioLow;
    private ImageView ivShare, ivRate;
    private SharedPrefManager prefManager;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_home);
        GeneralMethods.changeActivityFont(this);
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        MobileAds.initialize(this);
        initViews();
        checkAlarmSettings();
        initNotificationsAlarm();
    }

    private void initViews() {
        rlAzkarElMoslem = findViewById(R.id.rlAzkarElMoslem);
        rlAzkary = findViewById(R.id.rlAzkary);
        rlWerdElMohasba = findViewById(R.id.rlWerdMohasba);
        rlSettings = findViewById(R.id.rlSettings);
        rlSebha = findViewById(R.id.rlSebha);

        ivRate = findViewById(R.id.ivRate);
        ivShare = findViewById(R.id.ivShare);

        radioVeryHigh = findViewById(R.id.radioVeryHigh);
        radioHigh = findViewById(R.id.radioHigh);
        radioMedium = findViewById(R.id.radioMedium);
        radioLow = findViewById(R.id.radioLow);

        ivShare.setOnClickListener(this);
        ivRate.setOnClickListener(this);
        rlAzkarElMoslem.setOnClickListener(this);
        rlAzkary.setOnClickListener(this);
        rlSettings.setOnClickListener(this);
        rlWerdElMohasba.setOnClickListener(this);
        rlSebha.setOnClickListener(this);
        radioVeryHigh.setOnClickListener(this);
        radioHigh.setOnClickListener(this);
        radioMedium.setOnClickListener(this);
        radioLow.setOnClickListener(this);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.e("Tag", "ad loaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.e("Tag", "ad failed loading  " + errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.e("Tag", "ad opened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.e("Tag", "on ad left application");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.e("Tag", "ad closed");
            }
        });
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

    private void initNotificationsAlarm() {
        GeneralMethods.initOverLayZekrAlarm(this);
        if (prefManager.isSabahTimeDefault()) {
           GeneralMethods.initAzkarSabahAlarm(this);
           GeneralMethods.initAzkarMasaaAlarm(this);
           GeneralMethods.initAzkarNoomAlarm(this);
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
                prefManager.setAlarmManagerRepeatTime(Constants.ConstantsValues.VeryHighRepeat);
                prefManager.setOverLayCalendar(Calendar.getInstance());
                GeneralMethods.initOverLayZekrAlarm(HomeScreenActivity.this);
                break;

            case R.id.radioHigh:
                radioVeryHigh.setChecked(false);
                radioHigh.setChecked(true);
                radioMedium.setChecked(false);
                radioLow.setChecked(false);
                prefManager.setAlarmManagerRepeatTime(Constants.ConstantsValues.HighRepeat);
                prefManager.setOverLayCalendar(Calendar.getInstance());
                GeneralMethods.initOverLayZekrAlarm(HomeScreenActivity.this);
                break;

            case R.id.radioMedium:
                radioVeryHigh.setChecked(false);
                radioHigh.setChecked(false);
                radioMedium.setChecked(true);
                radioLow.setChecked(false);
                prefManager.setAlarmManagerRepeatTime(Constants.ConstantsValues.MediumRepeat);
                prefManager.setOverLayCalendar(Calendar.getInstance());
                GeneralMethods.initOverLayZekrAlarm(HomeScreenActivity.this);
                break;

            case R.id.radioLow:
                radioVeryHigh.setChecked(false);
                radioHigh.setChecked(false);
                radioMedium.setChecked(false);
                radioLow.setChecked(true);
                prefManager.setAlarmManagerRepeatTime(Constants.ConstantsValues.LowRepeat);
                prefManager.setOverLayCalendar(Calendar.getInstance());
                GeneralMethods.initOverLayZekrAlarm(HomeScreenActivity.this);
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
                startActivity(new Intent(HomeScreenActivity.this, SebhaAzkarActivity.class));
                break;
            case R.id.ivRate:
                GeneralMethods.openAppOnGooglePlay(HomeScreenActivity.this);
                break;
            case R.id.ivShare:
                GeneralMethods.shareApp(HomeScreenActivity.this);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}

