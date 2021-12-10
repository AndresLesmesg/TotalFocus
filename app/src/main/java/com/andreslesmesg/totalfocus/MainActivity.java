package com.andreslesmesg.totalfocus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.andreslesmesg.totalfocus.controller.CategoryController;
import com.andreslesmesg.totalfocus.controller.CourseController;
import com.andreslesmesg.totalfocus.controller.NoteController;
import com.andreslesmesg.totalfocus.controller.PostItController;
import com.andreslesmesg.totalfocus.controller.TimetableController;
import com.andreslesmesg.totalfocus.controller.auth.SessionController;
import com.andreslesmesg.totalfocus.databinding.ActivityMainBinding;
import com.andreslesmesg.totalfocus.view.CourseActivity;
import com.andreslesmesg.totalfocus.view.NoteActivity;
import com.andreslesmesg.totalfocus.view.PosItActivity;
import com.andreslesmesg.totalfocus.view.TimetableActivity;
import com.andreslesmesg.totalfocus.view.main.SectionsPagerAdapter;
import com.andreslesmesg.totalfocus.view.session.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 3;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init Controller
        CourseController.initCourse();
        NoteController.initNote();
        PostItController.initPostIt();
        TimetableController.initTimetable();
        CategoryController.initCategories();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Load to Fragment
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> {

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
        });

        checkPermissionReadExternalStorage();
        checkPermissionWriteExternalStorage();
        checkPermissionInternet();

        int permissionCheckNet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int permissionCheckWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionCheckRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            } case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            } case MY_PERMISSIONS_REQUEST_INTERNET:{
                if(grantResults.length > 2 && grantResults[2] == PackageManager.PERMISSION_GRANTED){

                }else {

                }

            }
        }

    }


    @Override
    public void finish() {
        navToLogin();
        super.finish();
    }

    private void navToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private void navToCourse(){
        Intent intent = new Intent(this, CourseActivity.class);
        intent.putExtra("position", -1);
        startActivity(intent);
    }


    private void navToNote(){
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("position", -1);
        startActivity(intent);
    }


    private void navToPostIt(){
        Intent intent = new Intent(this, PosItActivity.class);
        intent.putExtra("position", -1);
        startActivity(intent);
    }


    private void navToTimetable(){
        Intent intent = new Intent(this, TimetableActivity.class);
        intent.putExtra("position", -1);
        startActivity(intent);
    }

    private void checkPermissionReadExternalStorage(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            }
        }
    }
    private void checkPermissionWriteExternalStorage(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }
    }
    private void checkPermissionInternet(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        MY_PERMISSIONS_REQUEST_INTERNET);

            }
        }
    }
}
