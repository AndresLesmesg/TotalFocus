package com.andreslesmesg.totalfocus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.andreslesmesg.totalfocus.view.session.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null){
            navToNewActivity(LoginActivity.class, null, null);
        }else{
            navToNewActivity(MainActivity.class, user.getEmail(), user.getDisplayName());
        }

    }

    private void navToNewActivity(Class classRef, String email, String name){
        Intent intent = new Intent(this, classRef);

        if(email!=null && name!=null){
            intent.putExtra("name", name);
            intent.putExtra("email", email);
        }

        startActivity(intent);
        finish();
    }
}