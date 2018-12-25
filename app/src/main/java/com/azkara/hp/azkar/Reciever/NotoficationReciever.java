package com.azkara.hp.azkar.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Util.GeneralMethods;
import com.azkara.hp.azkar.Util.NotificationHelperUtils;

import java.util.Calendar;

import static com.azkara.hp.azkar.Util.Constants.IntentStrings.ZekrType;

public class NotoficationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Reciver", "received");
        NotificationHelperUtils notificationHelperUtils = new NotificationHelperUtils(context);
        SharedPrefManager prefManager = SharedPrefManager.getInstance().doStuff(context);
        int zekrType = intent.getIntExtra(ZekrType, 1);
        switch (zekrType) {
            case 1:
                Calendar calendar1 = prefManager.getAzkarSabahHour();
                if (checkIfTimeIsRight(calendar1)) {
                    notificationHelperUtils.buildNotification("أذكاري", "حان الان موعد أذكار الصباح");
                    calendar1.add(Calendar.DAY_OF_MONTH, 1);
                    prefManager.setAzkarSabahTime(calendar1);
                    GeneralMethods.initAzkarSabahAlarm(context);
                }
                break;
            case 2:
                Calendar calendar2 = prefManager.getAzkarMesaaHour();
                if (checkIfTimeIsRight(calendar2)) {
                    notificationHelperUtils.buildNotification("أذكاري", "حان الان موعد أذكار المساء");
                    calendar2.add(Calendar.DAY_OF_MONTH, 1);
                    prefManager.setAzkarMesaaTime(calendar2);
                    GeneralMethods.initAzkarMasaaAlarm(context);

                }
                break;
            case 3:
                Calendar calendar3 = prefManager.getAzkarNoomHour();
                if (checkIfTimeIsRight(calendar3)) {
                    notificationHelperUtils.buildNotification("أذكاري", "حان الان موعد أذكار قبل النوم");
                    calendar3.add(Calendar.DAY_OF_MONTH, 1);
                    prefManager.setAzkarNoomTime(calendar3);
                    GeneralMethods.initAzkarNoomAlarm(context);
                }
                break;
        }
    }

    private boolean checkIfTimeIsRight(Calendar zekrCalender) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY) == zekrCalender.get(Calendar.HOUR_OF_DAY);
    }

}
