package com.azkara.hp.azkar.Storage.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.azkara.hp.azkar.Util.Constants;

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

    public void setAlarmManagerRepeatTime(Long repeatTime){
        editor.putLong(Constants.SharedPreferencesTags.AlarmManagerTime, repeatTime);
        editor.apply();
    }

    public Long getAlarmManagerRepeatTime(){
        return preferences.getLong(Constants.SharedPreferencesTags.AlarmManagerTime,0);
    }

}
