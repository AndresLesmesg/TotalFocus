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

import com.andreslesmesg.totalfocus.MainActivity;
import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.controller.TimetableController;
import com.andreslesmesg.totalfocus.model.Timetable;

import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {

    private int hour, minute;
    private boolean notification;
    private String title;
    private ArrayList<Boolean> days = new ArrayList<Boolean>();

    private Button btn_save_timetable;
    private TextView btn_day_1, btn_day_2, btn_day_3, btn_day_4, btn_day_5, btn_day_6, btn_day_7;
    private EditText et_title_timetable;
    private TimePicker tp_timetable;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        for (int i = 0; i <7; i++) { days.add(true); }
        hour = -1;
        minute = -1;

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position = bundle.getInt("position");
        }
        if(position>-1){
            title = TimetableController.getTimetable(position).getTitle();
            hour = TimetableController.getTimetable(position).getHour();
            minute = TimetableController.getTimetable(position).getMin();
            days = TimetableController.getTimetable(position).getEnableDays();
        }

        et_title_timetable = findViewById(R.id.et_title_timetable);
        btn_save_timetable = findViewById(R.id.btn_save_timetable);
        tp_timetable = findViewById(R.id.tp_timetable);
        btn_day_1 = findViewById(R.id.btn_day_1);
        btn_day_2 = findViewById(R.id.btn_day_2);
        btn_day_3 = findViewById(R.id.btn_day_3);
        btn_day_4 = findViewById(R.id.btn_day_4);
        btn_day_5 = findViewById(R.id.btn_day_5);
        btn_day_6 = findViewById(R.id.btn_day_6);
        btn_day_7 = findViewById(R.id.btn_day_7);

        if(title!=null){
            setTitle(title);
            et_title_timetable.setText(title);
        }

        if(hour>-1 && minute>-1){
            tp_timetable.setHour(hour);
            tp_timetable.setMinute(minute);
        }

        if(position>-1){
            getStatusBtnDays();
        }

        btn_day_1.setOnClickListener(v -> btnDayClick(0, v));
        btn_day_2.setOnClickListener(v -> btnDayClick(1, v));
        btn_day_3.setOnClickListener(v -> btnDayClick(2, v));
        btn_day_4.setOnClickListener(v -> btnDayClick(3, v));
        btn_day_5.setOnClickListener(v -> btnDayClick(4, v));
        btn_day_6.setOnClickListener(v -> btnDayClick(5, v));
        btn_day_7.setOnClickListener(v -> btnDayClick(6, v));

        btn_save_timetable.setOnClickListener(v -> {
            saveTimetable();
        });
    }

    private void getStatusBtnDays() {
        if(!days.get(0)){
            btn_day_1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }
        if(!days.get(1)){
            btn_day_2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }
        if(!days.get(2)){
            btn_day_3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }
        if(!days.get(3)){
            btn_day_4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }
        if(!days.get(4)){
            btn_day_5.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }
        if(!days.get(5)){
            btn_day_6.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }
        if(!days.get(6)){
            btn_day_7.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_400)));
        }

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

    private void saveTimetable() {

        int hour_ = tp_timetable.getHour();
        int min_ = tp_timetable.getMinute();
        String title_ = et_title_timetable.getText().toString();

        if(!title_.equals("")){
            if(position>-1){
                TimetableController.setTimetable(position,new Timetable(title_, hour_, min_, notification, days));
            }else {
                TimetableController.addTimetable(new Timetable(title_, hour_, min_, false, days));
            }
        }

        finish();
    }

}
