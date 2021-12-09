package com.andreslesmesg.totalfocus.utilis;

import java.util.UUID;

public class GenerateId {

    public static String newId(String tag) {
        String id = tag+UUID.randomUUID().toString();
        return id;
    }
}
