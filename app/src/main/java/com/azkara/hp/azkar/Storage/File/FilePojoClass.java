package com.azkara.hp.azkar.Storage.File;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilePojoClass {
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("messages1")
    @Expose
    private List<String> messages1 = null;
    @SerializedName("messages2")
    @Expose
    private List<String> messages2 = null;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getMessages1() {
        return messages1;
    }

    public void setMessages1(List<String> messages1) {
        this.messages1 = messages1;
    }

    public List<String> getMessages2() {
        return messages2;
    }

    public void setMessages2(List<String> messages2) {
        this.messages2 = messages2;
    }
}
