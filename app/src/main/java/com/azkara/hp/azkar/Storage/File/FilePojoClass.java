package com.azkara.hp.azkar.Storage.File;

import com.azkara.hp.azkar.Model.AzkarElMoslem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilePojoClass {
    @SerializedName("version")
    @Expose
    private int version;
    @SerializedName("azkar")
    @Expose
    private List<AzkarElMoslem> azkar = null;

    public FilePojoClass() {
    }

    public FilePojoClass(int version, List<AzkarElMoslem> azkar) {
        this.version = version;
        this.azkar = azkar;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<AzkarElMoslem> getAzkar() {
        return azkar;
    }

    public void setAzkar(List<AzkarElMoslem> azkar) {
        this.azkar = azkar;
    }
}
