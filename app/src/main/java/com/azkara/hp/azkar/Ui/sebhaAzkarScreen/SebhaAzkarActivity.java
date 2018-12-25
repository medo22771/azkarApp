package com.azkara.hp.azkar.Ui.sebhaAzkarScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.GeneralMethods;

import java.util.ArrayList;

public class SebhaAzkarActivity extends AppCompatActivity {
    private ImageView btnBack;
    private RecyclerView rvSebhaAzkar;
    private ArrayList<String> sebhaAzkar;
    private SebhaAzkarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebha_azkar);
        GeneralMethods.changeActivityFont(this);
        initViews();
    }

    private void initViews() {
        rvSebhaAzkar = findViewById(R.id.rvSebhaAzkar);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSebhaAzkar.setLayoutManager(manager);
        rvSebhaAzkar.setItemAnimator(new DefaultItemAnimator());
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SharedPrefManager prefManager = SharedPrefManager.getInstance().doStuff(this);
        sebhaAzkar = prefManager.getAzkarSebhaData();
        adapter = new SebhaAzkarAdapter(sebhaAzkar, this);
        rvSebhaAzkar.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
