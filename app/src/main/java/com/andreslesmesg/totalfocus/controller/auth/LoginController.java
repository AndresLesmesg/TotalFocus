package com.andreslesmesg.totalfocus.controller.auth;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.andreslesmesg.totalfocus.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginController {
    public static void login(Activity activity, String email, String password){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();
                        }else {
                            Toast.makeText(activity, "Error al intentar Iniciar Sesion", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
