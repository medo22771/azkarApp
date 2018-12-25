package com.azkara.hp.azkar.Ui.Azkary_Screen;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.azkara.hp.azkar.Model.Azkary;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AzkaryActivity extends AppCompatActivity {
    ImageView btnBack;
    FloatingActionButton fabAddZekr;
    RecyclerView rvAzkary;
    AzkaryAdapter adapter;
    SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_azkary);
        GeneralMethods.changeActivityFont(this);
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        MobileAds.initialize(this);
        initViews();
        setData();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        fabAddZekr = findViewById(R.id.fabAddZekr);
        rvAzkary = findViewById(R.id.rvAzkary);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAzkary.setLayoutManager(manager);
        rvAzkary.setItemAnimator(new DefaultItemAnimator());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fabAddZekr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewZekr();
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void setData() {
        adapter = new AzkaryAdapter(prefManager.getAzkaryData(this));
        rvAzkary.setAdapter(adapter);
    }

    private void saveData() {
        prefManager.setAzkaryData(adapter.getAzkaries());
        finish();
    }

    private void addNewZekr() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_add_new_azkary, null);
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);
        GeneralMethods.changeViewFont(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        final EditText edAddZekr = dialogView.findViewById(R.id.edAddZekr);
        Button btnAddZekr = dialogView.findViewById(R.id.btnAddZekr);

        btnAddZekr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                String newZekrText = edAddZekr.getText().toString().trim();
                if (!newZekrText.isEmpty()) {
                    adapter.addNewZekr(new Azkary(newZekrText));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        saveData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }
}
