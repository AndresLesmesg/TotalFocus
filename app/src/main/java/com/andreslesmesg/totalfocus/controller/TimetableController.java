package com.andreslesmesg.totalfocus.controller;

import com.andreslesmesg.totalfocus.model.Timetable;

import java.util.ArrayList;

public class TimetableController {
    private static ArrayList<Timetable> tiemtables;

    public static void initTimetable(){
        if(tiemtables==null){
            tiemtables = new ArrayList<>();
        }
    }

    public  Timetable getTimetable(int id){
        return tiemtables.get(id);
    }

    public static ArrayList<Timetable> getTimetables() {
        return tiemtables;
    }

    public static void setTimetable(int id, Timetable tiemtable){
        tiemtables.set(id, tiemtable);
    }
    public static void addTimetable(Timetable tiemtable){
        tiemtables.add(tiemtable);
    }

    public static void deleteTimetable(int id){
        tiemtables.remove(id);
    }

    public static void pullTimetables(){

    }

    public static void pushTimetables(){

    }
}
