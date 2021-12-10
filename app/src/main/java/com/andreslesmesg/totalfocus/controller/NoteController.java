package com.andreslesmesg.totalfocus.controller;

import com.andreslesmesg.totalfocus.model.Note;

import java.util.ArrayList;

public class NoteController {
    private static ArrayList<Note> notes;

    public static void initNote(){
        if(notes==null){
            notes = new ArrayList<>();
        }
    }

    public static Note getNote(int id){
        return notes.get(id);
    }

    public static ArrayList<Note> getNotes() {
        return notes;
    }

    public static void setNote(int id, Note note){
        notes.set(id, note);
    }

    public static void addNote(Note note){
        notes.add(note);
    }

    public static void deleteNote(int id){
        if(notes!=null && notes.size()>id){
            notes.remove(id);
        }
    }

}
