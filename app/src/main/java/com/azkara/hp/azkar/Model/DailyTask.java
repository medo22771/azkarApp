package com.azkara.hp.azkar.Model;

import java.util.HashMap;

public class DailyTask {
    private HashMap<String, Boolean> taskPerDay;
    private int type;
    private String taskName ;

    public DailyTask(int type , String taskName) {
        this.type = type;
        taskPerDay = new HashMap<>();
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

    public void setDayToBeChecked(String dayName) {
        taskPerDay.put(dayName, true);
    }

    public int getCheckedDaysSize() {
        return taskPerDay.values().size();
    }

    public boolean isDayChecked(String dayName) {
        return taskPerDay.containsKey(dayName) ;
    }
}
