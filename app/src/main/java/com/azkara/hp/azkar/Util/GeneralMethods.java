package com.azkara.hp.azkar.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by NaderNabil216@gmail.com on 7/15/2018.
 */
public class GeneralMethods {
    public static void Init_Alarm(Context context) {
        Long repeatTime = SharedPrefManager.getInstance().doStuff(context).getAlarmManagerRepeatTime();
        if (repeatTime != 0) {
            Intent startIntent = new Intent(Constants.IntentStrings.SHOW_OVERLAY_VIEW);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), repeatTime, pendingIntent);
        }
    }

    /**
     * call it when user turn on availability
     */
    public static void Remove_Alarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent startIntent = new Intent(Constants.IntentStrings.SHOW_OVERLAY_VIEW);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 1, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    public static ArrayList<DailyTask> getDefaultData(Context context) {
        String[] tasksStrings = context.getResources().getStringArray(R.array.Tasks);
        ArrayList<DailyTask> data = new ArrayList<>();
        for (int counter = 0; counter < tasksStrings.length; counter++) {
            data.add(new DailyTask(getTypeOfCell(counter), tasksStrings[counter]));
        }
        return data;
    }


    private static int getTypeOfCell(int rowPosition) {
        int type;
        switch (rowPosition) {
            case 0:
            case 1:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
                type = Constants.CellsType.TYPE_SALAH;
                break;
            case 2:
            case 3:
            case 9:
            case 10:
            case 13:
            case 16:
                type = Constants.CellsType.TYPE_ZEKR;
                break;
            case 4:
                type = Constants.CellsType.TYPE_QOUR2AN;
                break;
            case 20:
                type = Constants.CellsType.TYPE_SEYAM;
                break;
            default:
                type = Constants.CellsType.TYPE_SALAH;
        }
        return type;
    }

    public static void checkTheme(AppCompatActivity activity) {
        int selectedTheme = SharedPrefManager.getInstance().doStuff(activity).getThemeColor();
        if (selectedTheme == Constants.ConstantsValues.LightTheme) {
            activity.setTheme(R.style.LightAppTheme);
        } else if (selectedTheme == Constants.ConstantsValues.DarkTheme) {
            activity.setTheme(R.style.DarkAppTheme);
        } else if (selectedTheme == Constants.ConstantsValues.GreenTheme) {
            activity.setTheme(R.style.GreenAppTheme);
        } else if (selectedTheme == Constants.ConstantsValues.PinkTheme) {
            activity.setTheme(R.style.PinkAppTheme);
        }
    }
}
