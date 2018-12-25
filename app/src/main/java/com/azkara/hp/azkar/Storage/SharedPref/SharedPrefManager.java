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
import java.util.Calendar;

import static com.azkara.hp.azkar.Util.Constants.ConstantsValues.LargeFont;

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

    public void setAlarmManagerRepeatTime(int repeatTime) {
        editor.putInt(Constants.SharedPreferencesTags.AlarmManagerTime, repeatTime);
        editor.apply();
    }

    public int getAlarmManagerRepeatTime() {
        return preferences.getInt(Constants.SharedPreferencesTags.AlarmManagerTime, Constants.ConstantsValues.NoRepeat);
    }

    public void setOverLayCalendar(Calendar overLayCalendar) {
        editor.putString(Constants.SharedPreferencesTags.OverlayCalendar, new Gson().toJson(overLayCalendar));
        editor.apply();
    }

    public Calendar getOverlayCalendar() {
        String calendarString = preferences.getString(Constants.SharedPreferencesTags.OverlayCalendar, "");
        return calendarString.isEmpty() ? Calendar.getInstance() : new Gson().fromJson(calendarString, Calendar.class);
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
            data = GeneralMethods.getDefaultWerdMohasbaData(context);
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

    public ArrayList<Azkary> getAzkaryData(Context context) {
        String dataJson = "";
        ArrayList<Azkary> data;
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Azkary>>() {
        }.getType();
        dataJson = preferences.getString(Constants.SharedPreferencesTags.Azkary, "");
        data = gson.fromJson(dataJson, type);
        if (data == null || data.isEmpty()) {
            data = GeneralMethods.getDefaultAzkaryData(context);
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
            data = GeneralMethods.getDefaultAzkarSebha(context);
            setAzkarSebhaData(data);
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

    public void setSebhaCount(int count) {
        editor.putInt(Constants.SharedPreferencesTags.SebhaCount, count);
        editor.apply();
    }

    public int getSebhaCount() {
        return preferences.getInt(Constants.SharedPreferencesTags.SebhaCount, 33);
    }

    public void setSebhaVibrate(boolean isVibrate) {
        editor.putBoolean(Constants.SharedPreferencesTags.SebhaVibrate, isVibrate);
        editor.apply();
    }

    public boolean getSebhaVibrate() {
        return preferences.getBoolean(Constants.SharedPreferencesTags.SebhaVibrate, false);
    }

    public void setAzkarVibrate(boolean isVibrate) {
        editor.putBoolean(Constants.SharedPreferencesTags.AzkarVibrate, isVibrate);
        editor.apply();
    }

    public boolean getAzkarVibrate() {
        return preferences.getBoolean(Constants.SharedPreferencesTags.AzkarVibrate, false);
    }

    public void setZekrDisappears(boolean canDisappears) {
        editor.putBoolean(Constants.SharedPreferencesTags.ZekrDisappear, canDisappears);
        editor.apply();
    }

    public boolean canDisappears() {
        return preferences.getBoolean(Constants.SharedPreferencesTags.ZekrDisappear, true);
    }


    public void setAzkarElMoslemFontSize(int size) {
        editor.putInt(Constants.SharedPreferencesTags.AzkarElMoslemFontSize, size);
        editor.apply();
    }

    public void setAzkaryFontSize(int size) {
        editor.putInt(Constants.SharedPreferencesTags.AzkaryFontSize, size);
        editor.apply();
    }


    public void setFontFamily(int number) {
        editor.putInt(Constants.SharedPreferencesTags.SelectedFont, number);
        editor.apply();
    }

    public int getAzkarElMoslemFontSize() {
        return preferences.getInt(Constants.SharedPreferencesTags.AzkarElMoslemFontSize, LargeFont);
    }

    public int getAzkaryFontSize() {
        return preferences.getInt(Constants.SharedPreferencesTags.AzkaryFontSize, LargeFont);
    }

    public int getFontFamily() {
        return preferences.getInt(Constants.SharedPreferencesTags.SelectedFont, 1);
    }

    public void setAzkarSabahTime(Calendar calendar) {
        editor.putString(Constants.SharedPreferencesTags.AzkarSabahTime, new Gson().toJson(calendar));
        editor.apply();
    }

    public void setAzkarMesaaTime(Calendar calendar) {
        editor.putString(Constants.SharedPreferencesTags.AzkarMasaaTime, new Gson().toJson(calendar));
        editor.apply();
    }

    public void setAzkarNoomTime(Calendar calendar) {
        editor.putString(Constants.SharedPreferencesTags.AzkarNoomTime, new Gson().toJson(calendar));
        editor.apply();
    }

    public Calendar getAzkarSabahHour() {
        Calendar defaultCalendar = Calendar.getInstance();
        defaultCalendar.set(Calendar.HOUR_OF_DAY, 5);
        defaultCalendar.set(Calendar.MINUTE, 0);
        defaultCalendar.set(Calendar.SECOND, 0);
        String jsonCalendar = preferences.getString(Constants.SharedPreferencesTags.AzkarSabahTime, "");
        return jsonCalendar.isEmpty() ? defaultCalendar : new Gson().fromJson(jsonCalendar, Calendar.class);
    }

    public Calendar getAzkarMesaaHour() {
        Calendar defaultCalendar = Calendar.getInstance();
        defaultCalendar.set(Calendar.HOUR_OF_DAY, 17);
        defaultCalendar.set(Calendar.MINUTE, 0);
        defaultCalendar.set(Calendar.SECOND, 0);
        String jsonCalendar = preferences.getString(Constants.SharedPreferencesTags.AzkarMasaaTime, "");
        return jsonCalendar.isEmpty() ? defaultCalendar : new Gson().fromJson(jsonCalendar, Calendar.class);
    }

    public Calendar getAzkarNoomHour() {
        Calendar defaultCalendar = Calendar.getInstance();
        defaultCalendar.set(Calendar.HOUR_OF_DAY, 22);
        defaultCalendar.set(Calendar.MINUTE, 0);
        defaultCalendar.set(Calendar.SECOND, 0);
        String jsonCalendar = preferences.getString(Constants.SharedPreferencesTags.AzkarNoomTime, "");
        return jsonCalendar.isEmpty() ? defaultCalendar : new Gson().fromJson(jsonCalendar, Calendar.class);
    }

    public boolean isSabahTimeDefault(){
        String zekrTime = preferences.getString(Constants.SharedPreferencesTags.AzkarNoomTime, "");
        return zekrTime.isEmpty();
    }

}
