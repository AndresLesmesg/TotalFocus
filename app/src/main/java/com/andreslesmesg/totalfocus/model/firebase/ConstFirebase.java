package com.andreslesmesg.totalfocus.model.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConstFirebase {
    public static final String CATEGORIES = "categories";
    public static final String COURSES = "courses";
    public static final String NOTES = "notes";
    public static final String POST_ITS = "post-its";
    public static final String TIMETABLES = "timetables";
    public static final String USERS = "users";

    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
}
