package com.azkara.hp.azkar.Model;

public class DailyTask {
    private int type , sat = 0 , sun=0 , mon = 0 , tue=0 , wed=0 , thu=0 , fri=0 , markedDays =0;
    private String taskName ;

    public DailyTask(int type , String taskName) {
        this.type = type;
        this.taskName = taskName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
        if (sat==1){
            markedDays++;
        }else {
            markedDays--;
        }
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
        if (sun==1){
            markedDays++;
        }else {
            markedDays--;
        }
    }

    public int getMon() {
        return mon;
    }

    public void setMon(int mon) {
        this.mon = mon;
        if (mon==1){
            markedDays++;
        }else {
            markedDays--;
        }
    }

    public int getTue() {
        return tue;
    }

    public void setTue(int tue) {
        this.tue = tue;
        if (tue==1){
            markedDays++;
        }else {
            markedDays--;
        }
    }

    public int getWed() {
        return wed;
    }

    public void setWed(int wed) {
        this.wed = wed;
        if (wed==1){
            markedDays++;
        }else {
            markedDays--;
        }
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
        if (thu==1){
            markedDays++;
        }else {
            markedDays--;
        }
    }

    public int getFri() {
        return fri;
    }

    public void setFri(int fri) {
        this.fri = fri;
        if (fri==1){
            markedDays++;
        }else {
            markedDays--;
        }
    }

    public int getMarkedDays() {
        return markedDays;
    }

    public void setMarkedDays(int markedDays) {
        this.markedDays = markedDays;
    }
}
