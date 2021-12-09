package com.andreslesmesg.totalfocus.controller;

import com.andreslesmesg.totalfocus.model.Timetable;

import java.util.ArrayList;

public class TimetableController {
    private static ArrayList<Timetable> timetables;

    public static void initTimetable(){
        if(timetables==null){
            timetables = new ArrayList<>();
        }
    }

    public static Timetable getTimetable(int id){
        return timetables.get(id);
    }

    public static ArrayList<Timetable> getTimetables() {
        return timetables;
    }

    public static void setTimetable(int id, Timetable timetable){
        timetables.set(id, timetable);
    }

    public static void addTimetable(Timetable timetable){
        timetables.add(timetable);
    }

    public static void deleteTimetable(int id){
        if(timetables!=null && timetables.size()>id){
            timetables.remove(id);
        }
    }

    public static void pullTimetables(){

    }

    public static void pushTimetables(){

    }
}
