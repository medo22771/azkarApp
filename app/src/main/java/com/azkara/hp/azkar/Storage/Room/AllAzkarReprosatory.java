package com.azkara.hp.azkar.Storage.Room;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.azkara.hp.azkar.Model.AzkarElMoslem;

import java.util.List;

public class AllAzkarReprosatory {
    private AzkarElMuslimDao azkarElMuslimDao ;
    private AzkaryDao azkaryDao ;
    private LiveData<List<AzkarElMoslem>> azkarElMoslemsPerCategory;

    public AllAzkarReprosatory(Application application) {
        AzakElMuslemDataBase db = AzakElMuslemDataBase.getDatabase(application);
        azkarElMuslimDao = db.azkarElMuslimDao();
        azkaryDao = db.azkaryDao();
        azkarElMoslemsPerCategory = azkarElMuslimDao.getAllAzkarElMoslem();
    }

    public LiveData<List<AzkarElMoslem>> getAllAzkarElMoslem(){
        return azkarElMoslemsPerCategory;
    }
}
