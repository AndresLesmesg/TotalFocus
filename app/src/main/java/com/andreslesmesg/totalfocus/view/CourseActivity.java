package com.andreslesmesg.totalfocus.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.controller.CourseController;
import com.andreslesmesg.totalfocus.model.Course;
import com.andreslesmesg.totalfocus.utilis.GenerateId;

import java.io.File;
import java.io.FileOutputStream;

public class CourseActivity extends AppCompatActivity {

    private int position;
    private String title;
    private String category;
    private Uri path, imageUri;

    private Button btn_save_course, btn_add_image;
    private EditText et_title_course, et_category_course;
    private ImageView iv_course_preview;
    private Bitmap image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            position=bundle.getInt("position");
        }

        if(position>-1){
            path = CourseController.getCourse(position).getImageUri();
            title = CourseController.getCourse(position).getTitle();
            category = CourseController.getCourse(position).getCategory();
        }

        btn_save_course = findViewById(R.id.btn_save_course);
        btn_add_image = findViewById(R.id.btn_add_image);
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

        if(path!=null) {
            iv_course_preview.setImageURI(path);
        }

        btn_add_image.setOnClickListener(v -> loadImage());

        btn_save_course.setOnClickListener(v -> saveCourse());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            assert data != null;
            path = data.getData();
            iv_course_preview.setImageURI(path);
        }
    }

    private void saveCourse() {

        String title = et_title_course.getText().toString();

        if (CourseController.getCourses()!=null && !title.equals("")){
            if(position>-1){
                if(path!=null){

                    createImage();

                    String filename = path.toString();
                    filename = filename.substring(filename.lastIndexOf("/")+1);

                    if(saveImage(image, filename)){
                        CourseController.setCourse(position, new Course(et_title_course.getText().toString(), 1, imageUri));
                    }else{
                        CourseController.setCourse(position, new Course(et_title_course.getText().toString(), 1, path));
                    }
                }else{
                    CourseController.setCourse(position, new Course(et_title_course.getText().toString(), 1));
                }
            }else {
                if(path!=null){

                    createImage();
                    if(saveImage(image, GenerateId.newId("IMG-"))){
                        CourseController.addCourse( new Course(et_title_course.getText().toString(), 0, imageUri));
                    }else{
                        CourseController.addCourse(new Course(et_title_course.getText().toString(), 0, path));
                    }
                }else {
                    CourseController.addCourse(new Course(et_title_course.getText().toString(), 0));
                }

            }
        }
        finish();
    }

    private void createImage() {

        try {
            BitmapDrawable drawable = (BitmapDrawable) iv_course_preview.getDrawable();
            image = drawable.getBitmap();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean saveImage(Bitmap image, String fileName) {

        File direct = new File(Environment.getExternalStorageState()+File.pathSeparator+"images");

        if (!fileName.endsWith(".jpeg")){
            fileName+=".jpeg";
        }

        if (!direct.exists()) {
            direct.mkdirs();
        }

        File file = new File(direct, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (file.exists()) {
            imageUri = Uri.fromFile(file);
            return true;
        }
        return false;
    }

    @SuppressLint("IntentReset")
    private void loadImage() {
         Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Selecione una Aplicación"), 10);
    }

}
