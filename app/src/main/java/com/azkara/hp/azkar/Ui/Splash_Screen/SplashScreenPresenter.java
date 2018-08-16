package com.azkara.hp.azkar.Ui.Splash_Screen;

import android.content.Context;

import com.azkara.hp.azkar.Storage.File.FilePojoClass;
import com.azkara.hp.azkar.Storage.File.FileReprosatory;
import com.azkara.hp.azkar.Storage.SharedPref.SharedPrefManager;

public class SplashScreenPresenter {
    FileReprosatory fileReprosatory = new FileReprosatory();
    Context context ;
    SharedPrefManager prefManager ;

    public SplashScreenPresenter(Context context) {
        this.context = context;
    }

    public void checkFileVersion (){
        FilePojoClass pojoClass = fileReprosatory.getFileData(context);
        if (pojoClass.getVersion() != prefManager.getAzkarElMoslemFileVersion()){

        }
    }
}
