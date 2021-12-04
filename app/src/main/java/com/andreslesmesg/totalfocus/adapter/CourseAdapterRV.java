package com.andreslesmesg.totalfocus.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.model.Course;
import com.andreslesmesg.totalfocus.view.MainActivity;

import java.util.ArrayList;

public class CourseAdapterRV extends RecyclerView.Adapter<CourseAdapterRV.ViewHolder> {

    private Activity context;
    private final ArrayList<Course> courses;

    public CourseAdapterRV(ArrayList<Course> courses){
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_course,null,false);
        context = (Activity) view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadData(courses.get(position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_course;
        ImageView iv_course;
        TextView tv_title_course;
        TextView tv_category_course;
        ImageButton btn_more_course;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_course = itemView.findViewById(R.id.cv_course);
            iv_course = itemView.findViewById(R.id.iv_course);
            tv_title_course = itemView.findViewById(R.id.tv_title_course);
            tv_category_course = itemView.findViewById(R.id.tv_category_course);
            btn_more_course = itemView.findViewById(R.id.btn_more_course);
        }

        public void loadData(Course course) {
            iv_course.setImageURI(course.getImageUri());
            tv_title_course.setText(course.getTitle());
            tv_category_course.setText(course.getCategory());
        }
    }
}
