package com.azkara.hp.azkar.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.azkara.hp.azkar.Service.FloatingWidgetService;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.GeneralMethods;

import java.util.Calendar;
import java.util.logging.Handler;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class OverLayWindowReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("receiver", "received");
        int repeatTime = SharedPrefManager.getInstance().doStuff(context).getAlarmManagerRepeatTime();
        Calendar overLayCalendar = SharedPrefManager.getInstance().doStuff(context).getOverlayCalendar();
        overLayCalendar.add(Calendar.MINUTE, repeatTime);
        SharedPrefManager.getInstance().doStuff(context).setOverLayCalendar(overLayCalendar);
        GeneralMethods.initOverLayZekrAlarm(context);
//        Intent startServiceIntent = new Intent(context, FloatingWidgetService.class);
//        context.startService(startServiceIntent);

        Log.i("Gohary", "Here");
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(com.azkara.hp.azkar.Gohary.FloatingWidgetService.class).build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);


    }
}
