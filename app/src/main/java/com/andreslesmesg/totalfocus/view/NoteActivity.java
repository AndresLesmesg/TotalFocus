package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.MainActivity;
import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.controller.CourseController;
import com.andreslesmesg.totalfocus.controller.NoteController;
import com.andreslesmesg.totalfocus.model.Note;

public class NoteActivity extends AppCompatActivity {

    private int position;
    private boolean favorite;
    private String title, category, content;

    private EditText et_title_note, et_category_note, et_content_note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position = bundle.getInt("position");
        }

        if(position>-1){
            title = NoteController.getNote(position).getTitle();
            content = NoteController.getNote(position).getContent();
            category = NoteController.getNote(position).getCategory();
            favorite = NoteController.getNote(position).getFavorite();
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
            saveNote();
        });

    }


    private void saveNote() {

        String title_ = et_title_note.getText().toString();
        String content_ = et_content_note.getText().toString();

        if(!title_.equals("")){
            if(position>-1){
                /*Edit Note*/
                NoteController.setNote(position, new Note(title_, content_, 0, favorite));
            }else {
                /*New Note*/
                NoteController.addNote(new Note(title_, content_, 1));
            }
        }

        finish();
    }

}
