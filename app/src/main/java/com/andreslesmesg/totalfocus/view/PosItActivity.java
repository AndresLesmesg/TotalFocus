package com.andreslesmesg.totalfocus.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.controller.PostItController;
import com.andreslesmesg.totalfocus.model.PostIt;

public class PosItActivity extends AppCompatActivity {

    private int position;
    private boolean favorite;
    private String category, details, title;

    EditText et_category_post_it, et_details_post_it, et_title_post_it;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postit);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position = bundle.getInt("position");
        }

        if(position>-1){
            title = PostItController.getPostIt(position).getTitle();
            details = PostItController.getPostIt(position).getDetail();
            favorite = PostItController.getPostIt(position).getFavorite();
        }

        et_title_post_it = findViewById(R.id.et_title_postit);
        et_details_post_it = findViewById(R.id.et_details_post_it);
        et_category_post_it = findViewById(R.id.et_category_post_it);


        if(title!=null) {
            setTitle(title);
            et_title_post_it.setText(title);
        }

        if(details!=null) {
            et_details_post_it.setText(details);
        }

        if(category!=null) {
            et_category_post_it.setText(category);
        }

        Button btn_save_post_it = findViewById(R.id.btn_save_post_it);
        btn_save_post_it.setOnClickListener(v -> {
            savePostIt();
        });
    }

    private void savePostIt() {

        String title_ = et_title_post_it.getText().toString();
        String details_ = et_details_post_it.getText().toString();

        if(!title_.equals("") && !details_.equals("")){
            if(position>1){
                PostItController.setPostIt(position, new PostIt(title_, details_, favorite));
            }else {
                PostItController.addPostIt(new PostIt(title_, details_));
            }
        }
        setResult(RESULT_OK);
        finish();
    }
}
