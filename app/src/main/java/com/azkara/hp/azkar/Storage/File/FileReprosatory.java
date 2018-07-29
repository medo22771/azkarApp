package com.azkara.hp.azkar.Storage.File;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class FileReprosatory {

    private String readFromfile(Context context) {
        String text = "";
        try{
            InputStream inputStream = context.getAssets().open("data.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public FilePojoClass getFileData(Context context) {
        String file_string = readFromfile(context);
        Gson gson = new Gson();
        Type type = new TypeToken<FilePojoClass>() {
        }.getType();
        FilePojoClass pojo = gson.fromJson(file_string, type);
        return pojo==null? new FilePojoClass():pojo ;
    }
}
