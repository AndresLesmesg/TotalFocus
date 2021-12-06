package com.andreslesmesg.totalfocus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.andreslesmesg.totalfocus.view.MainActivity;
import com.andreslesmesg.totalfocus.view.session.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null){
            navToNewActivity(LoginActivity.class);
        }else{
            navToNewActivity(MainActivity.class);
        }

    }

    private void navToNewActivity(Class classRef){
        startActivity(new Intent(this, classRef));
        finish();
    }
}