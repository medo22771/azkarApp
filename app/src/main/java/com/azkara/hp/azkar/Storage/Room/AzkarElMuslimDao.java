package com.azkara.hp.azkar.Storage.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.azkara.hp.azkar.Model.AzkarElMoslem;

import java.util.List;

@Dao
public interface AzkarElMuslimDao {
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from AzkarElMoslemTable ")
    LiveData<List<AzkarElMoslem>> getAllAzkarElMoslem();

    @Query("SELECT * from AzkarElMoslemTable WHERE ZekrCategory = :categoryName ")
    LiveData<List<AzkarElMoslem>> getAllAzkarOfCategory(int categoryName);




    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(AzkarElMoslem azkarElMoslem);

    @Delete
    void deleteZekr(AzkarElMoslem azkarElMoslem);

    @Query("DELETE FROM AzkarElMoslemTable")
    void deleteAll();
}
