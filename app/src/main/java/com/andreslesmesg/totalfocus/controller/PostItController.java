package com.andreslesmesg.totalfocus.controller;

import com.andreslesmesg.totalfocus.model.PostIt;

import java.util.ArrayList;

public class PostItController {
    private static ArrayList<PostIt> postIts;

    public static void initPostIt(){
        if(postIts==null){
            postIts = new ArrayList<>();
        }
    }

    public static PostIt getPostIt(int id){
        return postIts.get(id);
    }

    public static ArrayList<PostIt> getPostIts() {
        return postIts;
    }

    public static void setPostIt(int id, PostIt postIt){
        postIts.set(id, postIt);
    }

    public static void addPostIt(PostIt postIt){
        postIts.add(postIt);
    }

    public static void deletePostIt(int id){
        if(postIts!=null && postIts.size()>id){
            postIts.remove(id);
        }
    }

}
