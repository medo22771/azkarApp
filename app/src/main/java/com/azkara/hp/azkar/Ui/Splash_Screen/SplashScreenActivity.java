package com.azkara.hp.azkar.Ui.Splash_Screen;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.azkara.hp.azkar.R;
import com.azkara.hp.azkar.Storage.File.FilePojoClass;
import com.azkara.hp.azkar.Storage.File.FileReprosatory;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;
import com.azkara.hp.azkar.Ui.Home_Screen.HomeScreenActivity;
import com.azkara.hp.azkar.Util.GeneralMethods;

public class SplashScreenActivity extends AppCompatActivity {
    private FileReprosatory fileReprosatory = new FileReprosatory();
    private SharedPrefManager prefManager;
    final private int MY_PERMISSIONS_REQUEST_OERLAY_PERMISSION = 4564;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefManager = SharedPrefManager.getInstance().doStuff(this);
        checkFileVersion();
        checkPermissions();

        ActivityCompat.requestPermissions(this, new String[]
                {
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.SYSTEM_ALERT_WINDOW
                }, 100
        );

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
                (AppOpsManager.OPSTR_SYSTEM_ALERT_WINDOW.equals("android:system_alert_window") &&
                true)
                )
        {
            Log.i("Gohary", "Done");
        }
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                askForPermission();
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashScreenActivity.this, HomeScreenActivity.class));
                    }
                }, 1000);

            }
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreenActivity.this, HomeScreenActivity.class));
                }
            }, 1000);

        }

    }

    private void askForPermission() {
        GeneralMethods.show_alert_dialoug(this,
                "برجاء إعطاء الصلاحيات للتطبيق لإظهار الأذكار علي الشاشة الخارجية",
                "تنبيه",
                false,
                "إعطاء الصلاحية",
                "إغلاق التطبيق",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, MY_PERMISSIONS_REQUEST_OERLAY_PERMISSION);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAffinity();
                    }
                });
    }

    public void checkFileVersion() {
        FilePojoClass pojoClass = fileReprosatory.getFileData(this);
        if (pojoClass.getVersion() != prefManager.getAzkarElMoslemFileVersion()) {
            prefManager.setAzkarElMoslemData(pojoClass.getAzkar());
            prefManager.setAzkarElMoslemFileVersion(pojoClass.getVersion());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_OERLAY_PERMISSION: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.canDrawOverlays(this)) {
                        startActivity(new Intent(SplashScreenActivity.this, HomeScreenActivity.class));
                    } else {
                        finishAffinity();
                    }
                }
                return;
            }
        }
    }

}
