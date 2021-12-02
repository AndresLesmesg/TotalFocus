package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;

public class NoteActivity extends AppCompatActivity {
    private Button btn_save_note;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        btn_save_note = findViewById(R.id.btn_save_note);
        btn_save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
