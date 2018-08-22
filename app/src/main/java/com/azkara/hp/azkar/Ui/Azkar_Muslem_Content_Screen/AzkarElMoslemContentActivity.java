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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_azkar_el_moslem_content);
        Intent intent = getIntent();
        if (intent != null) {
            azkarListId = intent.getIntExtra(Constants.IntentStrings.CategoryId, 1);
            azkarListName = intent.getStringExtra(Constants.IntentStrings.CategoryName);
        }
        prefManager = SharedPrefManager.getInstance().doStuff(this);
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
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAzkar.setLayoutManager(manager);
        rvAzkar.setItemAnimator(new DefaultItemAnimator());
        seekBar.setOnSeekBarChangeListener(this);
    }

    private void initPlayer() {

        mPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor afd = getAssets().openFd("AudioFile.mp3");
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mPlayer.prepare();
            mPlayer.setVolume(1f, 1f);
            mPlayer.setLooping(true);
            mPlayer.start();
            seekBar.setMax(mPlayer.getDuration());
            startSeekBar();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getLocalizedMessage());
        }
    }

    private void startSeekBar() {
        final Handler mHandler = new Handler();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mPlayer != null) {
                    int mCurrentPosition = mPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });
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
        adapter = new AzkarElMoslemContentAdapter(azkarElMoslems, this);
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
        }
    }

    private void playTrack() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.start();
        }
    }

    private void pauseTrack() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }

    private void stopTrack() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    private void initAdMob() {
        MobileAds.initialize(this);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                finish();
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (mPlayer != null && fromUser) {
            mPlayer.seekTo(progress * 1000);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
