package com.azkara.hp.azkar.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.azkara.hp.azkar.Model.Azkary;
import com.azkara.hp.azkar.Model.DailyTask;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

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

    public static ArrayList<String> getDefaultAzkarSebha(Context context) {
        return new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.SebhaAzkar)));
    }

    public static String getRundomZekr (Context context){
        SharedPrefManager prefManager = SharedPrefManager.getInstance().doStuff(context);
        ArrayList<Azkary> azkaryData = prefManager.getAzkaryData();
        if (azkaryData.isEmpty()){
            return "";
        }else {
            Random random = new Random();
            int randomPosition = random.nextInt(azkaryData.size());
            return azkaryData.get(randomPosition).getZekr_content();
        }
    }

    public static ProgressDialog show_progress_dialoug(Context context, String Body_text, boolean cancelable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(Body_text);
        progressDialog.show();
        return progressDialog;
    }

    public static void show_alert_dialoug(Context context, String body, String title, boolean cancelable, String positive_text, String negative_text, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener NoListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setCancelable(cancelable).setMessage(body);
        if (!title.isEmpty()) {
            builder.setTitle(title);
        }
        if (!positive_text.isEmpty()) {
            builder.setPositiveButton(positive_text, yesListener);
        }
        if (!negative_text.isEmpty()) {
            builder.setNegativeButton(negative_text, NoListener);
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
