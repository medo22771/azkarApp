package com.azkara.hp.azkar.Ui.Settings_Screen;

import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Ui.ChangeThemeScreen.ChangeThemeActivity;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.azkara.hp.azkar.Util.Constants.ConstantsValues.LargeFont;
import static com.azkara.hp.azkar.Util.Constants.ConstantsValues.MediumFont;
import static com.azkara.hp.azkar.Util.Constants.ConstantsValues.SmallFont;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvAzkarySize, tvAzkarElMoslemSize, tvFontFamily, tvChangeTheme, tvSabah, tvMesaa, tvNoom, tvSabahTime, tvMesaaTime, tvNoomTime;
    private SwitchCompat switchShowAzkary, switchZekrDesApear;
    private ImageView btnBack;
    private LinearLayout llSabah, llMesaa, llNoom;
    private InterstitialAd mInterstitialAd;
    private SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_settings);
        GeneralMethods.changeActivityFont(this);
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        MobileAds.initialize(this);
        initViews();
        initAdMob();
        setInitialData();
        setAzkarTimeInViews();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvAzkarySize = findViewById(R.id.tvAzkarySize);
        tvAzkarElMoslemSize = findViewById(R.id.tvAzkarElMoslemSize);
        tvFontFamily = findViewById(R.id.tvFontFamily);
        tvChangeTheme = findViewById(R.id.tvChangeTheme);
        switchShowAzkary = findViewById(R.id.switchShowAzkary);
        switchZekrDesApear = findViewById(R.id.swichDisApperZekr);
        tvSabah = findViewById(R.id.tvSabah);
        tvMesaa = findViewById(R.id.tvMesaa);
        tvNoom = findViewById(R.id.tvNoom);
        tvSabahTime = findViewById(R.id.tvSabahTime);
        tvMesaaTime = findViewById(R.id.tvMesaaTime);
        tvNoomTime = findViewById(R.id.tvNoomTime);
        llSabah = findViewById(R.id.llSabah);
        llMesaa = findViewById(R.id.llMesaa);
        llNoom = findViewById(R.id.llNoom);

        btnBack.setOnClickListener(this);
        tvChangeTheme.setOnClickListener(this);
        tvFontFamily.setOnClickListener(this);
        tvAzkarElMoslemSize.setOnClickListener(this);
        tvAzkarySize.setOnClickListener(this);
        switchZekrDesApear.setOnClickListener(this);
        switchShowAzkary.setOnClickListener(this);
        llSabah.setOnClickListener(this);
        llMesaa.setOnClickListener(this);
        llNoom.setOnClickListener(this);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void setInitialData() {
        switchZekrDesApear.setChecked(prefManager.canDisappears());
        if (prefManager.getAlarmManagerRepeatTime() != Constants.ConstantsValues.NoRepeat) {
            switchShowAzkary.setChecked(true);
        } else {
            switchShowAzkary.setChecked(false);
        }
    }

    private void setAzkarTimeInViews() {
        Calendar sabahCalendar, mesaaCalendar, noomCalendar;
        sabahCalendar = prefManager.getAzkarSabahHour();
        mesaaCalendar = prefManager.getAzkarMesaaHour();
        noomCalendar = prefManager.getAzkarNoomHour();

        tvSabahTime.setText(GeneralMethods.getFormatedTime(sabahCalendar.getTime()));
        tvMesaaTime.setText(GeneralMethods.getFormatedTime(mesaaCalendar.getTime()));
        tvNoomTime.setText(GeneralMethods.getFormatedTime(noomCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                onBackPressed();
                break;
            case R.id.tvAzkarySize:
                showFontSizeDialog(false);
                break;
            case R.id.tvAzkarElMoslemSize:
                showFontSizeDialog(true);
                break;
            case R.id.tvFontFamily:
                showFontFamilyDialog();
                break;
            case R.id.tvChangeTheme:
                goToChangeTheme();
                break;
            case R.id.switchShowAzkary:
                switchShowAzkary();
                break;
            case R.id.swichDisApperZekr:
                switchZekrDisapears();
                break;
            case R.id.llSabah:
                showTimePickerDialog(1);
                break;
            case R.id.llMesaa:
                showTimePickerDialog(2);
                break;
            case R.id.llNoom:
                showTimePickerDialog(3);
                break;
        }
    }

    private void goToChangeTheme() {
        startActivity(new Intent(this, ChangeThemeActivity.class));
    }

    private void showFontSizeDialog(final boolean isAzkarElMoslem) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_font_size, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);
        GeneralMethods.changeViewFont(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        final RadioButton rbSmall, rbMedium, rbLarge;

        rbSmall = dialogView.findViewById(R.id.rbSmall);
        rbMedium = dialogView.findViewById(R.id.rbMedium);
        rbLarge = dialogView.findViewById(R.id.rbLarge);

        rbSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbSmall.setChecked(true);
                alertDialog.dismiss();
                if (isAzkarElMoslem) {
                    prefManager.setAzkarElMoslemFontSize(SmallFont);
                } else {
                    prefManager.setAzkaryFontSize(SmallFont);
                }
            }
        });

        rbMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMedium.setChecked(true);
                alertDialog.dismiss();
                if (isAzkarElMoslem) {
                    prefManager.setAzkarElMoslemFontSize(MediumFont);
                } else {
                    prefManager.setAzkaryFontSize(MediumFont);
                }
            }
        });

        rbLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbLarge.setChecked(true);
                alertDialog.dismiss();
                if (isAzkarElMoslem) {
                    prefManager.setAzkarElMoslemFontSize(LargeFont);
                } else {
                    prefManager.setAzkaryFontSize(LargeFont);
                }
            }
        });

        int selectedFontSize;
        if (isAzkarElMoslem) {
            selectedFontSize = prefManager.getAzkarElMoslemFontSize();
        } else {
            selectedFontSize = prefManager.getAzkaryFontSize();
        }

        switch (selectedFontSize) {
            case SmallFont:
                rbSmall.setChecked(true);
                break;
            case MediumFont:
                rbMedium.setChecked(true);
                break;
            case LargeFont:
                rbLarge.setChecked(true);
                break;
        }

    }

    private void showFontFamilyDialog() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_font_family, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);
        final RadioButton rbFont1, rbFont2, rbFont3;
        final TextView tv1, tv2, tv3;

        rbFont1 = dialogView.findViewById(R.id.rbFont1);
        rbFont2 = dialogView.findViewById(R.id.rbFont2);
        rbFont3 = dialogView.findViewById(R.id.rbFont3);

        tv1 = dialogView.findViewById(R.id.tv1);
        tv2 = dialogView.findViewById(R.id.tv2);
        tv3 = dialogView.findViewById(R.id.tv3);

        tv1.setTypeface(GeneralMethods.changeFont1(this));
        tv2.setTypeface(GeneralMethods.changeFont2(this));
        tv3.setTypeface(GeneralMethods.changeFont3(this));

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        rbFont1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFont1.setChecked(true);
                alertDialog.dismiss();
                prefManager.setFontFamily(1);
            }
        });

        rbFont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFont2.setChecked(true);
                alertDialog.dismiss();
                prefManager.setFontFamily(2);
            }
        });

        rbFont3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFont3.setChecked(true);
                alertDialog.dismiss();
                prefManager.setFontFamily(3);
            }
        });

        int selectedFontFamily;
        selectedFontFamily = prefManager.getFontFamily();
        switch (selectedFontFamily) {
            case 1:
                rbFont1.setChecked(true);
                break;
            case 2:
                rbFont2.setChecked(true);
                break;
            case 3:
                rbFont3.setChecked(true);
                break;
        }

    }

    private void switchZekrDisapears() {
        prefManager.setZekrDisappears(switchZekrDesApear.isChecked());
    }

    private void switchShowAzkary() {
        if (switchShowAzkary.isChecked()) {
            GeneralMethods.initOverLayZekrAlarm(this);
            prefManager.setAlarmManagerRepeatTime(Constants.ConstantsValues.VeryHighRepeat);
        } else {
            GeneralMethods.removeOverLayZekrAlarm(this);
            prefManager.setAlarmManagerRepeatTime(Constants.ConstantsValues.NoRepeat);
        }
    }

    private void showTimePickerDialog(final int zekrType) {
        Calendar mCalendar = null;
        switch (zekrType) {
            case 1:
                mCalendar = prefManager.getAzkarSabahHour();
                break;
            case 2:
                mCalendar = prefManager.getAzkarMesaaHour();
                break;
            case 3:
                mCalendar = prefManager.getAzkarNoomHour();
                break;
        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        switch (zekrType) {
                            case 1:
                                Calendar calendar1 = new GregorianCalendar();
                                calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar1.set(Calendar.MINUTE, minute);
                                calendar1.set(Calendar.SECOND, 0);
                                prefManager.setAzkarSabahTime(calendar1);
                                GeneralMethods.initAzkarSabahAlarm(SettingsActivity.this);
                                break;
                            case 2:
                                Calendar calendar2 = Calendar.getInstance();
                                calendar2.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar2.set(Calendar.MINUTE, minute);
                                calendar2.set(Calendar.SECOND, 0);
                                prefManager.setAzkarMesaaTime(calendar2);
                                GeneralMethods.initAzkarMasaaAlarm(SettingsActivity.this);
                                break;
                            case 3:
                                Calendar calendar3 = Calendar.getInstance();
                                calendar3.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar3.set(Calendar.MINUTE, minute);
                                calendar3.set(Calendar.SECOND, 0);
                                prefManager.setAzkarNoomTime(calendar3);
                                GeneralMethods.initAzkarNoomAlarm(SettingsActivity.this);
                                break;
                        }
                        setAzkarTimeInViews();
                    }
                }, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();

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
