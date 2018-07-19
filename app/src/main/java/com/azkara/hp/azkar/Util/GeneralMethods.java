package com.azkara.hp.azkar.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;

import java.util.Calendar;

/**
 * Created by NaderNabil216@gmail.com on 7/15/2018.
 */
public class GeneralMethods {
    public static void Init_Alarm(Context context) {
        Long repeatTime = SharedPrefManager.getInstance().doStuff(context).getAlarmManagerRepeatTime();
        if (repeatTime !=0){
            Intent startIntent = new Intent(Constants.IntentStrings.SHOW_OVERLAY_VIEW);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),repeatTime, pendingIntent);
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
}
