package com.andreslesmesg.totalfocus.controller.auth;

public class SessionController {

    private static String userId;

    public static void initUserId(String id){
         userId = id;
    }

    public static String getUserId() {
        return userId;
    }
}
