package com.andreslesmesg.totalfocus.view.session;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.controller.auth.LoginController;
import com.andreslesmesg.totalfocus.utilis.ValidateEmail;
import com.andreslesmesg.totalfocus.view.MainActivity;
import com.andreslesmesg.totalfocus.R;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText et_email_login, et_password_login;
    private TextView tv_register;
    private View view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        view = findViewById(R.id.cl_login);

        et_email_login = findViewById(R.id.et_email_login);
        et_password_login = findViewById(R.id.et_password_login);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);

        et_email_login.addTextChangedListener(textWatcher);
        et_password_login.addTextChangedListener(textWatcher);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginController.login(LoginActivity.this, getEmail(), getPassword());
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToRegister();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       hiddenKeyboard(this, view);

        et_email_login.clearFocus();
        et_password_login.clearFocus();

        return true;
    }

    private final TextWatcher textWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            enable();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void enable() {
        String email = getEmail().trim();
        String password = getPassword().trim();

        if (ValidateEmail.validate(email) && password.length()>6){
            btn_login.setEnabled(true);
        }else{
            btn_login.setEnabled(false);
        }
    }

    private void hiddenKeyboard(Context context, View view){
        InputMethodManager inputMethodManager= (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private String getEmail() {
        return et_email_login.getText().toString();
    }

    private String getPassword() {
        return et_password_login.getText().toString();
    }

    private void navToRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
