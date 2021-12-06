package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;

public class PosItActivity extends AppCompatActivity {

    private int position;
    private String category, details, title;

    EditText et_category_post_it, et_details_post_it, et_title_post_it;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postit);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position = bundle.getInt("position");
            title = bundle.getString("title");
            details = bundle.getString("details");
            category = bundle.getString("category");
        }

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
            Intent intent = new Intent(PosItActivity.this, MainActivity.class);

            intent.putExtra("index", position);
            intent.putExtra("title", et_title_post_it.getText());
            intent.putExtra("content", et_details_post_it.getText());
            intent.putExtra("category", et_category_post_it.getText());

            startActivity(intent);
        });
    }
}
