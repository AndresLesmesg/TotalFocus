package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;

import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {

    private int hour, minute;
    private String title;
    private ArrayList<Boolean> days = new ArrayList<Boolean>();

    private Button btn_save_timetable;
    private TextView btn_day_1, btn_day_2, btn_day_3, btn_day_4, btn_day_5, btn_day_6, btn_day_7;
    private EditText et_title_timetable;
    private TimePicker tp_timetable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        for (int i = 0; i <7; i++) { days.add(true); }

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            hour = -1;
            minute = -1;
            title = bundle.getString("title");
            hour = bundle.getInt("hour");
            minute = bundle.getInt("minutes");
        }

        et_title_timetable = findViewById(R.id.et_title_timetable);
        tp_timetable = findViewById(R.id.tp_timetable);

        if(title!=null){
            setTitle(title);
            et_title_timetable.setText(title);
        }
        if (hour>0 && minute>0){
            tp_timetable.setHour(hour);
            tp_timetable.setMinute(minute);
        }

        btn_day_1 = findViewById(R.id.btn_day_1);
        btn_day_2 = findViewById(R.id.btn_day_2);
        btn_day_3 = findViewById(R.id.btn_day_3);
        btn_day_4 = findViewById(R.id.btn_day_4);
        btn_day_5 = findViewById(R.id.btn_day_5);
        btn_day_6 = findViewById(R.id.btn_day_6);
        btn_day_7 = findViewById(R.id.btn_day_7);

        btn_day_1.setOnClickListener(v -> btnDayClick(0, v));
        btn_day_2.setOnClickListener(v -> btnDayClick(1, v));
        btn_day_3.setOnClickListener(v -> btnDayClick(2, v));
        btn_day_4.setOnClickListener(v -> btnDayClick(3, v));
        btn_day_5.setOnClickListener(v -> btnDayClick(4, v));
        btn_day_6.setOnClickListener(v -> btnDayClick(5, v));
        btn_day_7.setOnClickListener(v -> btnDayClick(6, v));

        btn_save_timetable = findViewById(R.id.btn_save_timetable);
        btn_save_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimetableActivity.this, MainActivity.class);



                startActivity(intent);
            }
        });
    }

    private void btnDayClick(int item, View view) {
        if(!days.get(item)){
            days.set(item, true);
            view.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.teal_700)));

        }else {
            days.set(item, false);
            view.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }
    }

}
