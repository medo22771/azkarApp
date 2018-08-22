package com.azkara.hp.azkar.Ui.Azkar_Muslem_Screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.azkara.hp.azkar.Model.AzkarElMoslemCategory;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Util.GeneralMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class AzkarElMoslemActivity extends AppCompatActivity {
    AzkarElMoslemAdapter adapter;
    RecyclerView rvAzkarCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralMethods.checkTheme(this);
        setContentView(R.layout.activity_azkar_el_moslem);
        initViews();
        setData(generateAzkarCategoryList());
    }

    private void initViews() {
        rvAzkarCategories = findViewById(R.id.rvAzkarCategories);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvAzkarCategories.addItemDecoration(new GridSpacingItemDecoration(this, 2, true));
        rvAzkarCategories.setLayoutManager(mLayoutManager);
        rvAzkarCategories.setItemAnimator(new DefaultItemAnimator());
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

}
