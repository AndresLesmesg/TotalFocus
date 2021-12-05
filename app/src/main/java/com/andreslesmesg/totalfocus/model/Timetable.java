package com.andreslesmesg.totalfocus.model;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;

public class Timetable {

    public Timetable(String title) {
        this.title = title;
    }

    public Timetable(String title, String hour, String min, boolean notification, ArrayList<Boolean> enableDays) {
        this.title = title;
        this.hour = hour;
        this.min = min;
        this.notification = notification;
        this.enableDays = enableDays;
    }

    private String title;
    private String hour, min;
    private boolean notification;
    private ArrayList<Boolean> enableDays;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
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

}
