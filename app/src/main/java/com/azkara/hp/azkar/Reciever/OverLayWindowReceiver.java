package com.azkara.hp.azkar.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.azkara.hp.azkar.Service.FloatingWidgetService;

public class OverLayWindowReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context, FloatingWidgetService.class);
        context.startService(startServiceIntent);

    }
}
