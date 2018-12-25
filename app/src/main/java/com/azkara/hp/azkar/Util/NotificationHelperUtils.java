package com.azkara.hp.azkar.Util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Ui.Azkar_Muslem_Screen.AzkarElMoslemActivity;

import java.util.List;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

/**
 * Created by ah.khaled1994@gmail.com on 2018-07-17.
 */
public class NotificationHelperUtils extends ContextWrapper {
    private String channelID = "azkarId";
    private String channelName = "azkar";
    private NotificationManager notificationManager;

    public NotificationHelperUtils(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(Color.BLUE);
        channel.setVibrationPattern(new long[]{500, 500, 500, 500, 500});
        getNotificationManager().createNotificationChannel(channel);
    }

    private NotificationManager getNotificationManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public void buildNotification(String title, String body) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, AzkarElMoslemActivity.class), 0);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo))
                .setSmallIcon(R.drawable.ic_logo)
                .setTicker(title)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVisibility(VISIBILITY_PUBLIC)
                .setSound(defaultSoundUri)
                .setWhen(System.currentTimeMillis())
                .setVibrate(new long[]{500, 500, 500, 500, 500})
                .setLights(Color.BLUE, 3000, 3000);
        int NOTIFICATION_ID = (int) (long) (System.currentTimeMillis());
        getNotificationManager().notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    public void buildNotificationWithIntent(String title, String body, Intent intent) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo))
                .setSmallIcon(R.mipmap.ic_logo)
                .setTicker(title)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setVisibility(VISIBILITY_PUBLIC)
                .setSound(defaultSoundUri)
                .setPriority(Notification.PRIORITY_HIGH)
                .setWhen(System.currentTimeMillis())
                .setVibrate(new long[]{500, 500, 500, 500, 500})
                .setLights(Color.BLUE, 3000, 3000);
        int NOTIFICATION_ID = (int) (long) (System.currentTimeMillis());
        getNotificationManager().notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    public boolean isAppIsInBackground(Context mContext) {
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance
                    == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (String activeProcess : processInfo.pkgList) {
                    if (activeProcess.equals(mContext.getPackageName())) {
                        //If your app is the process in foreground,
                        // then it's not in running in background
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void playNotificationSound() {
        try {
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(this, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
