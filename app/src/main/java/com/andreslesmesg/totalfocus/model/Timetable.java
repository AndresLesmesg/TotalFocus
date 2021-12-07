package com.andreslesmesg.totalfocus.model;

import java.util.ArrayList;

public class Timetable {

    private String title;
    private int hour, min;
    private boolean notification;
    private ArrayList<Boolean> enableDays;
    private final String[] DAYS = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

    public Timetable(String title, int hour, int min, boolean notification, ArrayList<Boolean> enableDays) {
        this.title = title;
        this.hour = hour;
        this.min = min;
        this.notification = notification;
        this.enableDays = enableDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public ArrayList<Boolean> getEnableDays() {
        return enableDays;
    }

    public void setEnableDays(ArrayList<Boolean> enableDays) {
        this.enableDays = enableDays;
    }

    public String getDays() {

        String days="";
        int count = 0;

        for (int i = 0; i < DAYS.length; i++) {
            if (enableDays!=null){
                if (enableDays.get(i)){
                    count += 1;
                }
            }
        }

        for (int i = 0; i < DAYS.length; i++) {
            if (enableDays!=null){
                if (enableDays.get(i)){
                    days+=DAYS[i];
                    if (count>1){
                        days+=", ";
                        count -= 1;
                    }
                }
            }
        }
        if(days.length()>1){
            days+=".";
        }
        return days;
    }

    public String getAlarmHour() {
        return ""+hour+":"+min;
    }
}
