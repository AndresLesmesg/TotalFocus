package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;

import java.io.Serializable;

public class CourseActivity extends AppCompatActivity implements Serializable {

    private int position;
    private String title, category, image;

    private Button btn_save_course;
    private EditText et_title_course, et_category_course;
    private ImageView iv_course_preview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position=bundle.getInt("position");
            title=bundle.getString("title");
            category=bundle.getString("category");
            //image=bundle.getString("category");
        }

        btn_save_course = findViewById(R.id.btn_save_course);
        et_title_course = findViewById(R.id.et_title_course);
        et_category_course = findViewById(R.id.mactv_category_course);
        iv_course_preview = findViewById(R.id.iv_course_preview);

        if(title!=null) {
            setTitle(title);
            et_title_course.setText(title);
        }
        if(category!=null) {
            et_category_course.setText(category);
        }
        if(image!=null) {
            //load image
        }

        btn_save_course.setOnClickListener(v -> {
            Intent intent = new Intent(CourseActivity.this, MainActivity.class);
            intent.putExtra("index", position);
            intent.putExtra("title", et_title_course.getText());
            intent.putExtra("category", et_category_course.getText());
            //intent.putExtra("image", course.getImageUri());
           startActivity(intent);
        });
    }
}
