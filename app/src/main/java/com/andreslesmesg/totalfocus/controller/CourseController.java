package com.andreslesmesg.totalfocus.controller;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.andreslesmesg.totalfocus.model.Course;
import com.andreslesmesg.totalfocus.model.firebase.ConstFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseController {

    private static ArrayList<Course> courses;

    public static void initCourse(){
        if(courses==null){
            courses = new ArrayList<>();
            pullCourses();
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

    private static boolean checkExistCourse(String id){
        for (Course c : courses) {
            if(c.getId()==id){
                return true;
            }
        }
        return false;
    }

    public static void pushCourse(Context context, int id){

        Map<String, Object> course = new HashMap<>();
        course.put("title", courses.get(id).getTitle());
        course.put("category", courses.get(id).getCategory());
        if(courses.get(id).getImageUri()!=null){
            course.put("image", courses.get(id).getImageUri().toString());
        }

        try {
            String userId = ConstFirebase.currentUser.getUid();

            ConstFirebase.db.collection(ConstFirebase.USERS)
                    .document(userId)
                    .collection(ConstFirebase.COURSES)
                    .add(course)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            courses.get(id).setId(documentReference.getId());
                            Toast.makeText(context, "Se registro un nuevo curso", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error al intentar crear curso", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (NullPointerException e){
            e.getCause();
        }

    }

    public static void pullCourses(){

        try {
            String userId = ConstFirebase.currentUser.getUid();

            ConstFirebase.db.collection(ConstFirebase.USERS)
                    .document(userId)
                    .collection(ConstFirebase.COURSES)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    Course course;
                                    course = new Course(
                                            doc.getData().get("title").toString(),
                                            0);
                                    course.setId(doc.getId());
                                    if(doc.getData().get("image")!=null){
                                        course.setImageUri(Uri.parse(doc.getData().get("image").toString()));
                                    }
                                    if (!checkExistCourse(course.getId())){
                                        addCourse(course);
                                    }
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } catch (NullPointerException e){
        e.getCause();
    }

    }
}
