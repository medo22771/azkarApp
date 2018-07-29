package com.azkara.hp.azkar.Storage.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NaderNabil216@gmail.com on 7/16/2018.
 */
public class SharedPrefManager {
    private static final SharedPrefManager ourInstance = new SharedPrefManager();

    private final String STORAGE = "com.azkara.hp.azkar.Storage.SharedPref.STORAGE";
    SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private Context context;

    private SharedPrefManager() {
    }

    public static SharedPrefManager getInstance() {
        return ourInstance;
    }

    public SharedPrefManager doStuff(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        return ourInstance;
    }

    public void setAlarmManagerRepeatTime(Long repeatTime) {
        editor.putLong(Constants.SharedPreferencesTags.AlarmManagerTime, repeatTime);
        editor.apply();
    }

    public Long getAlarmManagerRepeatTime() {
        return preferences.getLong(Constants.SharedPreferencesTags.AlarmManagerTime, 0);
    }

    public void setCellsData(ArrayList<DailyTask> cellsData) {
        editor.putString(Constants.SharedPreferencesTags.CellsData, new Gson().toJson(cellsData));
        editor.apply();
    }

    public ArrayList<DailyTask> getCellsData(Context context) {
        String dataJson = "";
        ArrayList<DailyTask> data;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<DailyTask>>() {
        }.getType();
        dataJson = preferences.getString(Constants.SharedPreferencesTags.CellsData, "");
        data = gson.fromJson(dataJson, type);
        if (data == null || data.isEmpty()) {
            data = GeneralMethods.getDefaultData(context);
            return data;
        } else {
            return data;
        }
    }

}
