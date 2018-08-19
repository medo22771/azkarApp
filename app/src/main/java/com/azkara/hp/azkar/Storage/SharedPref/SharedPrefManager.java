package com.azkara.hp.azkar.Storage.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.azkara.hp.azkar.Model.Azkary;
import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.Util.Constants;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
        return preferences.getLong(Constants.SharedPreferencesTags.AlarmManagerTime, Constants.ConstantsValues.NoRepeat);
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

    public void setAzkarElMoslemData(ArrayList<AzkarElMoslem> azkarElMoslemData) {
        editor.putString(Constants.SharedPreferencesTags.AzkarElMoslem, new Gson().toJson(azkarElMoslemData));
        editor.apply();
    }

    public void setAzkaryData(ArrayList<Azkary> azkaryData) {
        editor.putString(Constants.SharedPreferencesTags.Azkary, new Gson().toJson(azkaryData));
        editor.apply();
    }

    public void setAzkarSebhaData(ArrayList<String> azkarSebhaData) {
        editor.putString(Constants.SharedPreferencesTags.AzkarSebha, new Gson().toJson(azkarSebhaData));
        editor.apply();
    }

    public ArrayList<AzkarElMoslem> getAzkarElmoslemData() {
        String dataJson = "";
        ArrayList<AzkarElMoslem> data;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<AzkarElMoslem>>() {
        }.getType();
        dataJson = preferences.getString(Constants.SharedPreferencesTags.AzkarElMoslem, "");
        data = gson.fromJson(dataJson, type);
        if (data == null || data.isEmpty()) {
            data = new ArrayList<>();
            return data;
        } else {
            return data;
        }
    }

    public ArrayList<Azkary> getAzkaryData() {
        String dataJson = "";
        ArrayList<Azkary> data;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Azkary>>() {
        }.getType();
        dataJson = preferences.getString(Constants.SharedPreferencesTags.Azkary, "");
        data = gson.fromJson(dataJson, type);
        if (data == null || data.isEmpty()) {
            data = new ArrayList<>();
            return data;
        } else {
            return data;
        }
    }

    public ArrayList<String> getAzkarSebhaData() {
        String dataJson = "";
        ArrayList<String> data;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        dataJson = preferences.getString(Constants.SharedPreferencesTags.AzkarSebha, "");
        data = gson.fromJson(dataJson, type);
        if (data == null || data.isEmpty()) {
            data = new ArrayList<>();
            return data;
        } else {
            return data;
        }
    }

    public void setAzkarElMoslemFileVersion(int version) {
        editor.putInt(Constants.SharedPreferencesTags.AzkarElMoslemFileVersion, version);
        editor.apply();
    }

    public void setAzkarFileVersion(int version) {
        editor.putInt(Constants.SharedPreferencesTags.AzkarFileVersion, version);
        editor.apply();
    }

    public int getAzkarElMoslemFileVersion() {
        return preferences.getInt(Constants.SharedPreferencesTags.AzkarElMoslemFileVersion, 0);
    }

    public int getAzkarFileVersion() {
        return preferences.getInt(Constants.SharedPreferencesTags.AzkarFileVersion, 0);
    }

    public void setThemeColor(int theme) {
        editor.putInt(Constants.SharedPreferencesTags.ThemeColor, theme);
        editor.apply();
    }

    public int getThemeColor() {
        return preferences.getInt(Constants.SharedPreferencesTags.ThemeColor, Constants.ConstantsValues.LightTheme);
    }
}
