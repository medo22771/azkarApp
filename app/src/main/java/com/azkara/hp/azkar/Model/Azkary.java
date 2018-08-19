package com.azkara.hp.azkar.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import com.azkara.hp.azkar.Util.Constants;

@Entity(tableName = Constants.RoomTags.AzkaryTable )
public class Azkary {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    @ColumnInfo(name = Constants.RoomTags.ZekrId)
    private int zekrId;

    @ColumnInfo(name = Constants.RoomTags.ZekrContentCol)
    private String zekr_content;

    private boolean checked = false ;

    public Azkary(String zekr_content) {
        this.zekr_content = zekr_content;
    }

    @Nullable
    public int getZekrId() {
        return zekrId;
    }

    public void setZekrId(@Nullable int zekrId) {
        this.zekrId = zekrId;
    }

    public String getZekr_content() {
        return zekr_content;
    }

    public void setZekr_content(String zekr_content) {
        this.zekr_content = zekr_content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
