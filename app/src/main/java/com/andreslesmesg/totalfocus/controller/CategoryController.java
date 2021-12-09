package com.andreslesmesg.totalfocus.controller;


import java.util.ArrayList;

public class CategoryController {
    private static ArrayList<String> categories;

    public static void initCategories(){
        if(categories==null){
            categories = new ArrayList<>();
        }
    }

    public String getCourse(int id){
        return categories.get(id);
    }

    public static ArrayList<String> getCategories() {
        return categories;
    }

    public static void setCourse(int id, String category){
        categories.set(id, category);
    }
    public static void addCourse(String category){
        categories.add(category);
    }

    public static void deleteCourse(int id){
        categories.remove(id);
    }

    public static void pullCategories(){

    }

    public static void pushCategories(){

    }
}
