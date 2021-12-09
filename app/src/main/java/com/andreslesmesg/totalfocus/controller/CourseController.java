package com.andreslesmesg.totalfocus.controller;

import com.andreslesmesg.totalfocus.model.Course;
import java.util.ArrayList;

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

    public static void pullCourses(){

    }

    public static void pushCourses(){

    }
}
