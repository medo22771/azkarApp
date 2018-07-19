package com.azkara.hp.azkar.Storage.Room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.azkara.hp.azkar.Model.Azkary;

@Database(entities = {AzkarElMoslem.class, Azkary.class}, version = 1 , exportSchema = false)
public abstract class AzakElMuslemDataBase extends RoomDatabase {

    public abstract AzkarElMuslimDao azkarElMuslimDao();

    public abstract AzkaryDao azkaryDao();

    private static AzakElMuslemDataBase INSTANCE;

    static AzakElMuslemDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AzakElMuslemDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AzakElMuslemDataBase.class, "AzakElMuslemDataBase")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AzkarElMuslimDao mDao;

        PopulateDbAsync(AzakElMuslemDataBase db) {
            mDao = db.azkarElMuslimDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            AzkarElMoslem zekr = new AzkarElMoslem("zekr1", 2, "info 1", 1);
            mDao.insert(zekr);
            return null;
        }
    }

}
