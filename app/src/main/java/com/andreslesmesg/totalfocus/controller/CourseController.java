package com.andreslesmesg.totalfocus.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.andreslesmesg.totalfocus.model.Course;
import com.andreslesmesg.totalfocus.model.firebase.ConstFirebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseController {

    private static ArrayList<Course> courses;

    public static void initCourse(){
        if(courses==null){
            courses = new ArrayList<>();
        }
    }

    public static Course getCourse(int id){
        return courses.get(id);
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourse(int id, Course course){
        courses.set(id, course);
    }

    public static void addCourse(Course course){
        courses.add(course);
    }

    public static void deleteCourse(int id){
        if(courses!=null && courses.size()>id){
            courses.remove(id);
        }
    }

    public static void pullCourse(FirebaseFirestore db, int id){

        Map<String, Object> course = new HashMap<>();
        course.put("title", courses.get(id).getTitle());

            db.collection("courses")
                .add(course)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    public static void pushCourses(){

    }
}
