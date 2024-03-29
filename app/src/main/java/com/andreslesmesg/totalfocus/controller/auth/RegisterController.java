package com.andreslesmesg.totalfocus.controller.auth;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.andreslesmesg.totalfocus.model.firebase.ConstFirebase;
import com.andreslesmesg.totalfocus.model.firebase.User;
import com.andreslesmesg.totalfocus.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class RegisterController {

    public static void register(Context context, String name, String email, String password){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userFirestore(context, name, email);
                        }else {
                            Toast.makeText(context, "Error al intentar Registrase", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private static void userFirestore(Context context, String name, String email) {

        try{
            String id = ConstFirebase.currentUser.getUid();

            long timeCreate = ConstFirebase.currentUser.getMetadata().getCreationTimestamp();
            User user = new User(id, name, email, timeCreate);

            ConstFirebase.db.collection(ConstFirebase.USERS)
                    .document(id)
                    .set(user, SetOptions.merge())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.putExtra("email",user.getId());
                                context.startActivity(intent);
                            }else {
                                Toast.makeText(context, "Error al intentar Guardar datos del Usiario", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }catch (NullPointerException e){
            e.getCause();
        }

    }
}
