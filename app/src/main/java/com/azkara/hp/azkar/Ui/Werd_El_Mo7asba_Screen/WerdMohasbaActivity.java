package com.azkara.hp.azkar.Ui.Werd_El_Mo7asba_Screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;

import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.GeneralMethods;

import java.util.ArrayList;

public class WerdMohasbaActivity extends AppCompatActivity {

    private ArrayList<DailyTask> dailyTasks;
    private SharedPrefManager sharedPrefManager;
    private RecyclerView recyclerView;
    private FixedGridLayoutManager manager;
    private HorizontalScrollView horizontalScrollView;
    private WerdMohasbaAdapter adapter;
    private int scrollX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_werd_mohasba);
        sharedPrefManager = SharedPrefManager.getInstance().doStuff(this);
        initViews();
        setData();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rvTasks);
        horizontalScrollView = findViewById(R.id.headerScroll);
        manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        recyclerView.setLayoutManager(new FixedGridLayoutManager());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                scrollX += dx;
                horizontalScrollView.scrollTo(scrollX, 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void setData() {
      //  dailyTasks=sharedPrefManager.getCellsData(this,getResources().getStringArray(R.array.Tasks).length);
        adapter=new WerdMohasbaAdapter(GeneralMethods.getDefaultData(this),this);
        recyclerView.setAdapter(adapter);
    }

    private void resetData(){

    }

    private void calculateRate(){

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
