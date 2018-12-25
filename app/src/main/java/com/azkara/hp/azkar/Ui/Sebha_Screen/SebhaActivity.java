package com.azkara.hp.azkar.Ui.Sebha_Screen;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.easyandroidanimations.library.FadeInAnimation;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class SebhaActivity extends AppCompatActivity implements View.OnClickListener {

    private InterstitialAd mInterstitialAd;
    private ImageView btnBack, ivReset;
    private RadioButton radio33, radio100, radio1000, radioUnLimited;
    private RelativeLayout rvTasbeh;
    private TextView tvTotalCount, tvCurrentCount, tvZekr;
    private int currentCount = 0, totalCount = 0, countPreference = 0, currentZekrIndex = 0;
    private SharedPrefManager prefManager;
    private SwitchCompat switchVibrate;
    private boolean isVibrate = false;
    private String sebhaZekr="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_sebha);
        GeneralMethods.changeActivityFont(this);
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        isVibrate = prefManager.getSebhaVibrate();
        countPreference = prefManager.getSebhaCount();
        Intent intent = getIntent();
        if (intent!=null){
            sebhaZekr=intent.getStringExtra(Constants.IntentStrings.SebhaZekr);
        }
        MobileAds.initialize(this);
        initViews();
        initAdMob();
        checkSebhaCount();
        setTextData();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        ivReset = findViewById(R.id.ivReset);
        radio33 = findViewById(R.id.radio33);
        radio100 = findViewById(R.id.radio100);
        radio1000 = findViewById(R.id.radio1000);
        radioUnLimited = findViewById(R.id.radioUnLimited);
        rvTasbeh = findViewById(R.id.rvTasbeh);
        switchVibrate = findViewById(R.id.switchVibrate);
        tvCurrentCount = findViewById(R.id.tvCurrentCount);
        tvTotalCount = findViewById(R.id.tvTotalCount);
        tvZekr = findViewById(R.id.tvZekr);

        btnBack.setOnClickListener(this);
        ivReset.setOnClickListener(this);
        radio33.setOnClickListener(this);
        radio100.setOnClickListener(this);
        radio1000.setOnClickListener(this);
        radioUnLimited.setOnClickListener(this);
        rvTasbeh.setOnClickListener(this);
        switchVibrate.setOnClickListener(this);

        isVibrate = prefManager.getSebhaVibrate();
        switchVibrate.setChecked(isVibrate);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tvZekr.setText(sebhaZekr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.ivReset:
                reset();
                break;
            case R.id.radio33:
                prefManager.setSebhaCount(33);
                countPreference = 33;
                radio33.setChecked(true);
                radio100.setChecked(false);
                radio1000.setChecked(false);
                radioUnLimited.setChecked(false);
                break;
            case R.id.radio100:
                prefManager.setSebhaCount(100);
                countPreference = 100;
                radio33.setChecked(false);
                radio100.setChecked(true);
                radio1000.setChecked(false);
                radioUnLimited.setChecked(false);
                break;
            case R.id.radio1000:
                prefManager.setSebhaCount(1000);
                countPreference = 1000;
                radio33.setChecked(false);
                radio100.setChecked(false);
                radio1000.setChecked(true);
                radioUnLimited.setChecked(false);
                break;
            case R.id.radioUnLimited:
                prefManager.setSebhaCount(10000);
                countPreference = 10000;
                radio33.setChecked(false);
                radio100.setChecked(false);
                radio1000.setChecked(false);
                radioUnLimited.setChecked(true);
                break;
            case R.id.rvTasbeh:
                performTasbeh();
                break;
            case R.id.switchVibrate:
                switchVibration();
                break;

        }
    }

    private void checkSebhaCount() {
        switch (countPreference) {
            case 33:
                radio33.setChecked(true);
                break;
            case 100:
                radio100.setChecked(true);
                break;
            case 1000:
                radio1000.setChecked(true);
                break;
            case 10000:
                radioUnLimited.setChecked(true);
                break;
        }
    }

    private void reset() {
        totalCount = 0;
        currentCount = 0;
        setTextData();
    }

    private void performTasbeh() {
        new FadeInAnimation(rvTasbeh)
                .setDuration(500)
                .animate();
        totalCount++;
        currentCount++;
        if (currentCount >= countPreference) {
            currentCount = 0;
            vibrate(1000);
        } else {
            if (isVibrate) {
                vibrate(500);
            }
        }
        setTextData();
    }

    private void vibrate(int millis) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(millis);
        }
    }


    private void setTextData() {
        tvTotalCount.setText(String.format("الإجمالي : %d", totalCount));
        tvCurrentCount.setText(String.format("العدد الحالى : %d", currentCount));
    }

    private void switchVibration() {
        isVibrate = switchVibrate.isChecked();
        prefManager.setSebhaVibrate(switchVibrate.isChecked());
    }

    private void initAdMob() {
        MobileAds.initialize(this);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1058946254502124/3991378142");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            finish();
        }
    }

}
