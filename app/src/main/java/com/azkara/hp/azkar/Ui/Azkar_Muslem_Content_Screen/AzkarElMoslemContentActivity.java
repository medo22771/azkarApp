package com.azkara.hp.azkar.Ui.Azkar_Muslem_Content_Screen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;
import java.util.ArrayList;

public class AzkarElMoslemContentActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private TextView tvTitle;
    private ImageView btnPlay, btnPause, btnBack;
    private RecyclerView rvAzkar;
    private SeekBar seekBar;
    private String azkarListName = "";
    private int azkarListId = 0;
    private SharedPrefManager prefManager;
    private ArrayList<AzkarElMoslem> azkarElMoslems = new ArrayList<>();
    private ProgressDialog progressDialog;
    private InterstitialAd mInterstitialAd;
    private MediaPlayer mPlayer;
    private String LOG_TAG = "Media Player";
    private AzkarElMoslemContentAdapter adapter;
    private Handler mSeekbarUpdateHandler = new Handler();
    private Runnable mUpdateSeekbar;
    private int categoryPosition;
    private RelativeLayout layout_player;
    private SwitchCompat switchVibrate;
    private boolean isVibrate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_azkar_el_moslem_content);
        GeneralMethods.changeActivityFont(this);
        Intent intent = getIntent();
        if (intent != null) {
            azkarListId = intent.getIntExtra(Constants.IntentStrings.CategoryId, 1);
            azkarListName = intent.getStringExtra(Constants.IntentStrings.CategoryName);
            categoryPosition = intent.getIntExtra(Constants.IntentStrings.CategoryPosition, 3);
        }
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        MobileAds.initialize(this);
        initViews();
        initAdMob();
        initPlayer();
        progressDialog = GeneralMethods.show_progress_dialoug(this, "جاري التحميل", false);
        tvTitle.setText(azkarListName);
        filterData();
        setData();
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        seekBar = findViewById(R.id.seekBar);
        btnPause = findViewById(R.id.btnPause);
        btnPlay = findViewById(R.id.btnPlay);
        btnBack = findViewById(R.id.btnBack);
        rvAzkar = findViewById(R.id.rvAzkar);
        layout_player = findViewById(R.id.layout_player);
        switchVibrate = findViewById(R.id.switchVibrate);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAzkar.setLayoutManager(manager);
        rvAzkar.setItemAnimator(new DefaultItemAnimator());
        seekBar.setOnSeekBarChangeListener(this);
        btnBack.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        switchVibrate.setOnClickListener(this);
        isVibrate = prefManager.getAzkarVibrate();
        switchVibrate.setChecked(isVibrate);
        AdView mAdView = findViewById(R.id.adView);
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

    private void initPlayer() {
        String fileName = "";
        if (categoryPosition < 2) {
            if (categoryPosition == 0) {
                fileName = "sabah.mp3";
            } else if (categoryPosition == 1) {
                fileName = "masaa.mp3";
            }
            mPlayer = new MediaPlayer();
            try {
                AssetFileDescriptor afd = getAssets().openFd(fileName);
                mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mPlayer.prepare();
                mPlayer.setLooping(true);
                seekBar.setMax(mPlayer.getDuration());
            } catch (IOException e) {
                Log.e(LOG_TAG, e.getLocalizedMessage());
            }
        } else {
            layout_player.setVisibility(View.GONE);
        }
    }

    private void startSeekBar() {
        mUpdateSeekbar = new Runnable() {
            @Override
            public void run() {
                if (mPlayer != null) {
                    seekBar.setProgress(mPlayer.getCurrentPosition());
                }
                mSeekbarUpdateHandler.postDelayed(this, 50);
            }
        };
        mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);

    }

    private void filterData() {
        ArrayList<AzkarElMoslem> list = prefManager.getAzkarElmoslemData();
        for (AzkarElMoslem azkarElMoslem : list) {
            if (azkarElMoslem.getZekrCategory() == azkarListId) {
                azkarElMoslems.add(azkarElMoslem);
            }
        }
    }

    private void setData() {
        progressDialog.dismiss();
        adapter = new AzkarElMoslemContentAdapter(azkarElMoslems, this, isVibrate);
        rvAzkar.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                stopTrack();
                onBackPressed();
                break;
            case R.id.btnPlay:
                playTrack();
                break;
            case R.id.btnPause:
                pauseTrack();
                break;
            case R.id.switchVibrate:
                switchVibration();
                break;
        }
    }

    private void switchVibration() {
        isVibrate = switchVibrate.isChecked();
        prefManager.setAzkarVibrate(switchVibrate.isChecked());
        adapter.setIsVibrate(isVibrate);
    }

    private void playTrack() {
        if (mPlayer != null) {
            mPlayer.start();
            startSeekBar();
        }else {
            initPlayer();
            playTrack();
        }
    }

    private void pauseTrack() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
        }
    }

    private void stopTrack() {
        if (mPlayer != null) {
            mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
            mPlayer.release();
            mPlayer = null;
        }
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
                stopTrack();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            finish();
            stopTrack();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTrack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTrack();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mPlayer.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
