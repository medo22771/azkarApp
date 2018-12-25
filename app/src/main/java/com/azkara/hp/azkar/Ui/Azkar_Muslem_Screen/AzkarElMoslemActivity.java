package com.azkara.hp.azkar.Ui.Azkar_Muslem_Screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.azkara.hp.azkar.Model.AzkarElMoslemCategory;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Arrays;

public class AzkarElMoslemActivity extends AppCompatActivity {
    private AzkarElMoslemAdapter adapter;
    private RecyclerView rvAzkarCategories;
    private AdView mAdView;
    private ImageView btnBack ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_azkar_el_moslem);
        GeneralMethods.changeActivityFont(this);
        MobileAds.initialize(this);
        initViews();
        setData(generateAzkarCategoryList());
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        rvAzkarCategories = findViewById(R.id.rvAzkarCategories);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvAzkarCategories.addItemDecoration(new GridSpacingItemDecoration(this, 2, true));
        rvAzkarCategories.setLayoutManager(mLayoutManager);
        rvAzkarCategories.setItemAnimator(new DefaultItemAnimator());
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setData(ArrayList<AzkarElMoslemCategory> categories) {
        adapter = new AzkarElMoslemAdapter(this, categories);
        rvAzkarCategories.setAdapter(adapter);
    }

    private ArrayList<AzkarElMoslemCategory> generateAzkarCategoryList() {
        ArrayList<AzkarElMoslemCategory> categories = new ArrayList<>();
        ArrayList<String> categoriesTitle = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.AzkarCategories)));
        for (int counter = 0; counter < categoriesTitle.size(); counter++) {
            categories.add(new AzkarElMoslemCategory(counter + 1, categoriesTitle.get(counter)));
        }
        return categories;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
