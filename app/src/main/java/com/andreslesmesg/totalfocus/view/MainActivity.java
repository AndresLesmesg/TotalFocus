package com.andreslesmesg.totalfocus.view;

import android.content.Intent;
import android.os.Bundle;

import com.andreslesmesg.totalfocus.view.session.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.andreslesmesg.totalfocus.view.main.SectionsPagerAdapter;
import com.andreslesmesg.totalfocus.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Boolean sessionStatus;
    private int index_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

        }


        //Load to Fragment
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (binding.viewPager.getCurrentItem()){
                    case 0:
                        navToCourse();
                        break;
                    case 1:
                        navToNote();
                        break;
                    case 2:
                        navToPostIt();
                        break;
                    case 3:
                        navToTimetable();
                        break;
                }
            }

        });
    }

    private void navToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void navToCourse(){
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
    }
    private void navToNote(){
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }
    private void navToPostIt(){
        Intent intent = new Intent(this, PosItActivity.class);
        startActivity(intent);
    }
    private void navToTimetable(){
        Intent intent = new Intent(this, TimetableActivity.class);
        startActivity(intent);
    }

}
