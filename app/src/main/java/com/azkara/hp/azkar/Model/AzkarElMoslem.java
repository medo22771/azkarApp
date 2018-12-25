package com.azkara.hp.azkar.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import com.azkara.hp.azkar.Util.Constants;

@Entity(tableName = Constants.RoomTags.AzkarElMoslemTable)
public class AzkarElMoslem {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    @ColumnInfo(name = Constants.RoomTags.ZekrId)
    private int zekrId ;

    @ColumnInfo(name = Constants.RoomTags.ZekrContentCol)
    private String zekrContent = "";

    @ColumnInfo(name = Constants.RoomTags.ZekrCountCol)
    private int zekrCount ;

    @ColumnInfo(name = Constants.RoomTags.ZekrInfoCol)
    private String zekrInfo = "";

    @ColumnInfo(name = Constants.RoomTags.ZekrCategory)
    private int zekrCategory ;

    public AzkarElMoslem(String zekrContent, int zekrCount, String zekrInfo, int zekrCategory) {
        this.zekrContent = zekrContent;
        this.zekrCount = zekrCount;
        this.zekrInfo = zekrInfo;
        this.zekrCategory = zekrCategory;
    }

    public int getZekrId() {
        return zekrId;
    }

    public void setZekrId(int zekrId) {
        this.zekrId = zekrId;
    }

    public String getZekrContent() {
        return zekrContent;
    }

    public void setZekrContent(String zekrContent) {
        this.zekrContent = zekrContent;
    }

    public int getZekrCount() {
        return zekrCount;
    }

    public void setZekrCount(int zekrCount) {
        this.zekrCount = zekrCount;
    }

    public String getZekrInfo() {
        return zekrInfo;
    }

    public void setZekrInfo(String zekrInfo) {
        this.zekrInfo = zekrInfo;
    }

    public int getZekrCategory() {
        return zekrCategory;
    }

    public void setZekrCategory(int zekrCategory) {
        this.zekrCategory = zekrCategory;
    }

    public void decreaseCount(){
        if (zekrCount>0){
        this.zekrCount--;
        }
    }

    public boolean isCountEqualZero(){
        return zekrCount==0;
    }
}
