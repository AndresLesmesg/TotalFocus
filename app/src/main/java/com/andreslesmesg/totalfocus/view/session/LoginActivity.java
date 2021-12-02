package com.andreslesmesg.totalfocus.view.session;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.view.MainActivity;
import com.andreslesmesg.totalfocus.R;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private TextView tv_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToHome();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToRegister();
            }
        });

    }

    private void navToRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void navToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
