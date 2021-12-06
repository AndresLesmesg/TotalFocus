package com.andreslesmesg.totalfocus.utilis;

import android.util.Patterns;

import java.util.regex.Pattern;

public class ValidateEmail {
    public static boolean validate(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
