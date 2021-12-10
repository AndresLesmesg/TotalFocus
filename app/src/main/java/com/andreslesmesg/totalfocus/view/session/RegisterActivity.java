package com.andreslesmesg.totalfocus.view.session;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.MainActivity;
import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.controller.auth.RegisterController;
import com.andreslesmesg.totalfocus.utilis.HiddenKeyboard;
import com.andreslesmesg.totalfocus.utilis.ValidateEmail;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_register;
    private EditText et_first_name_register,et_last_name_register,
            et_email_register, et_passwd1_register, et_passwd2_register;
    private TextView tv_login;
    private View cl_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cl_register = findViewById(R.id.cl_register);

        btn_register = findViewById(R.id.btn_register);
        tv_login = findViewById(R.id.tv_login);

        et_first_name_register = findViewById(R.id.et_first_name_register);
        et_last_name_register = findViewById(R.id.et_last_name_register);
        et_email_register = findViewById(R.id.et_email_register);
        et_passwd1_register = findViewById(R.id.et_passwd1_register);
        et_passwd2_register = findViewById(R.id.et_passwd2_register);

        et_first_name_register.addTextChangedListener(textWatcher);
        et_last_name_register.addTextChangedListener(textWatcher);
        et_email_register.addTextChangedListener(textWatcher);
        et_passwd1_register.addTextChangedListener(textWatcher);
        et_passwd2_register.addTextChangedListener(textWatcher);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterController.register(RegisterActivity.this,
                        getName(), getEmail(), getPassword());
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navToLogin();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        HiddenKeyboard.hidden(this, cl_register);

        et_first_name_register.clearFocus();
        et_last_name_register.clearFocus();
        et_email_register.clearFocus();
        et_passwd1_register.clearFocus();
        et_passwd2_register.clearFocus();

        return true;
    }

    private TextWatcher textWatcher = new TextWatcher() {
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
        String name = getName().trim();
        String email = getEmail().trim();
        String passwd1 = getPassword().trim();
        String passwd2 = getConfirmPassword().trim();

        if(name.length()>6 && ValidateEmail.validate(email)
                && passwd1.length()>6 && passwd1.equals(passwd2)) {
            btn_register.setEnabled(true);
        }else {
            btn_register.setEnabled(true);
        }

    }

    private String getPassword() {
        return et_passwd1_register.getText().toString();
    }

    private String getConfirmPassword() {
        return et_passwd2_register.getText().toString();
    }

    private String getEmail() {
        return et_email_register.getText().toString();
    }

    private String getName() {
        return et_first_name_register.getText().toString() + " "
                 + et_last_name_register.getText().toString();
    }

    // navigation
    private void navToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
