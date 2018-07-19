package com.azkara.hp.azkar.Storage.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.azkara.hp.azkar.Model.Azkary;

import java.util.List;

@Dao
public interface AzkaryDao {
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from AzkaryTable ")
    LiveData<List<Azkary>> getAllAzkarElMoslem();

    @Query("SELECT * from AzkaryTable ")
    Azkary[] getAllAzkarElMoslemArrayList();

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Azkary azkary);

    @Delete
    void deleteZekr(Azkary azkary);

    @Query("DELETE FROM AzkaryTable")
    void deleteAll();
}
