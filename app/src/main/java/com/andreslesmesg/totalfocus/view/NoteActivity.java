package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;

public class NoteActivity extends AppCompatActivity {

    private int position;
    private String title, category, content;

    private EditText et_title_note, et_category_note, et_content_note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position = bundle.getInt("position");
            title = bundle.getString("title");
            category = bundle.getString("category");
            content = bundle.getString("content");
        }

        et_title_note = findViewById(R.id.et_title_note);
        et_content_note = findViewById(R.id.et_content_note);
        et_category_note = findViewById(R.id.et_category_note);

        if(title!=null) {
            setTitle(title);
            et_title_note.setText(title);
        }

        if(content!=null) {
            et_content_note.setText(category);
        }

        if(category!=null) {
            et_category_note.setText(category);
        }


        Button btn_save_note = findViewById(R.id.btn_save_note);
        btn_save_note.setOnClickListener(v -> {
            Intent intent = new Intent(NoteActivity.this, MainActivity.class);

            intent.putExtra("index", position);
            intent.putExtra("title", et_title_note.getText());
            intent.putExtra("content", et_content_note.getText());
            intent.putExtra("category", et_category_note.getText());

            startActivity(intent);
        });

    }
}
