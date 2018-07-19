package com.azkara.hp.azkar.Ui.Home_Screen;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.Room.AllAzkarReprosatory;

import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AllAzkarReprosatory reprosatory = new AllAzkarReprosatory(getApplication());
        reprosatory.getAllAzkarElMoslem().observe(this, new Observer<List<AzkarElMoslem>>() {
            @Override
            public void onChanged(@Nullable List<AzkarElMoslem> azkarElMoslems) {
                for (AzkarElMoslem azkarElMoslem : azkarElMoslems){
                    Log.e("nader",azkarElMoslem.getZekrContent());
                }
            }
        });
    }
}
