package com.azkara.hp.azkar.Ui.Werd_El_Mo7asba_Screen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class WerdMohasbaActivity extends AppCompatActivity {

    private ArrayList<DailyTask> dailyTasks;
    private SharedPrefManager sharedPrefManager;
    private RecyclerView recyclerView;
    private FixedGridLayoutManager manager;
    private HorizontalScrollView horizontalScrollView;
    private WerdMohasbaAdapter adapter;
    private int scrollX = 0;
    private InterstitialAd mInterstitialAd;
    private Toolbar toolbar;
    private Button btnCalculate, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_werd_mohasba);
        sharedPrefManager = SharedPrefManager.getInstance().doStuff(this);
        initViews();
        initAdMob();
        setData();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rvTasks);
        horizontalScrollView = findViewById(R.id.headerScroll);
        manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        recyclerView.setLayoutManager(new FixedGridLayoutManager());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollX += dx;
                horizontalScrollView.scrollTo(scrollX, 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRate();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetData();
            }
        });
    }

    private void setData() {
        dailyTasks = sharedPrefManager.getCellsData(this);
        adapter = new WerdMohasbaAdapter(dailyTasks, this);
        recyclerView.setAdapter(adapter);
    }


    private void resetData() {
        dailyTasks = GeneralMethods.getDefaultData(this);
        adapter.notifyDataSetChanged();
    }

    private void calculateRate() {
        ProgressDialog progressDialog = GeneralMethods.show_progress_dialoug(this, "جاري احتساب الورد", false);
        String totalProgres = calculateTasks();
        progressDialog.dismiss();
        GeneralMethods.show_alert_dialoug(this, totalProgres, "إجمالي ورد المحاسبة", true, "حسنا", "", null, null);

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
    protected void onDestroy() {
        sharedPrefManager.setCellsData(adapter.getDailyTasks());
        super.onDestroy();
    }

    private String calculateTasks() {
        ArrayList<DailyTask> tasks = adapter.getDailyTasks();
        int totalSalah = 91, totalZekr = 42, totalQour2an = 7, totalSeyam = 2;
        int salah = 0, zekr = 0, qor2an = 0, seyam = 0;
        for (DailyTask task : tasks) {
            switch (task.getType()) {
                case Constants.CellsType.TYPE_SALAH:
                    salah += task.getMarkedDays();
                    break;
                case Constants.CellsType.TYPE_ZEKR:
                    zekr += task.getMarkedDays();
                    break;
                case Constants.CellsType.TYPE_QOUR2AN:
                    qor2an += task.getMarkedDays();
                    break;
                case Constants.CellsType.TYPE_SEYAM:
                    seyam += task.getMarkedDays();
                    break;
            }
        }

        return String.format("%s : %s/%s\n%s : %s/%s\n%s : %s/%s\n%s : %s/%s\n",
                "الصلوات المقامة", "" + salah, "" + totalSalah,
                "تلاوة الأذكار", "" + zekr, "" + totalZekr
                , "تلاوة القرءان", "" + qor2an, "" + totalQour2an
                , "الصيام", "" + seyam, "" + totalSeyam);
    }

}
