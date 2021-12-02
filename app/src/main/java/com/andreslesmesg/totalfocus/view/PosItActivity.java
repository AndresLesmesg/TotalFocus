package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;

public class PosItActivity extends AppCompatActivity {

    private Button btn_save_postit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postit);

        btn_save_postit = findViewById(R.id.btn_save_postit);
        btn_save_postit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PosItActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
