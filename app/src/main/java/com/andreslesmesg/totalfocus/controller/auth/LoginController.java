package com.andreslesmesg.totalfocus.controller.auth;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.andreslesmesg.totalfocus.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginController {

    public static void login(Activity activity, String email, String password){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(activity, MainActivity.class);
                            String id = getUserId();
                            intent.putExtra("user_id",id);
                            activity.startActivity(intent);
                            activity.finish();
                        }else {
                            Toast.makeText(activity, "Error al intentar Iniciar Sesion", Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }
    private static String getUserId() {
        try {
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String id = firebaseUser.getUid();
            return id;
        } catch (NullPointerException e){
            e.getCause();
        }
        return null;
    }
}
