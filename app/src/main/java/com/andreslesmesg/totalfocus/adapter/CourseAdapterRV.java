package com.andreslesmesg.totalfocus.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.model.Course;
import com.andreslesmesg.totalfocus.view.CourseActivity;

import java.util.ArrayList;

public class CourseAdapterRV extends RecyclerView.Adapter<CourseAdapterRV.ViewHolder> {

    private Activity context;
    private final ArrayList<Course> courses;

    public CourseAdapterRV(ArrayList<Course> courses){
        this.courses = courses;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_course,null,false);
        context = (Activity) view.getContext();
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadData(courses.get(position));
        holder.btn_more_course.setOnClickListener(v -> holder.showMenu(courses.get(position), position));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_course;
        private final TextView tv_title_course;
        private final TextView tv_category_course;
        private final ImageButton btn_more_course;
        private final PopupMenu menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CardView cv_course = itemView.findViewById(R.id.cv_course);
            iv_course = itemView.findViewById(R.id.iv_course);
            tv_title_course = itemView.findViewById(R.id.tv_title_course);
            tv_category_course = itemView.findViewById(R.id.tv_category_course);
            btn_more_course = itemView.findViewById(R.id.btn_more_course);
            menu = new PopupMenu(itemView.getContext(), btn_more_course);

        }

        public void loadData(Course course) {
            iv_course.setImageURI(course.getImageUri());
            tv_title_course.setText(course.getTitle());
            tv_category_course.setText(course.getCategory());
        }

        public void removeItem(int position) {
            courses.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position, courses.size());
        }

        @SuppressLint("NonConstantResourceId")
        public void showMenu(Course course, int position) {
            menu.getMenu().clear(); //Clear Menu - Fix Generate infinite Menu
            menu.getMenuInflater().inflate(R.menu.menu_resource, menu.getMenu());
            menu.setOnMenuItemClickListener(item -> {

                switch (item.getItemId()){
                    case R.id.item_edit:
                        Intent intent = new Intent(itemView.getContext(), CourseActivity.class);
                        intent.putExtra("index", position);
                        intent.putExtra("title", course.getTitle());
                        intent.putExtra("category", course.getCategory());
                        intent.putExtra("image", course.getImageUri());
                        context.startActivity(intent);
                        break;
                    case R.id.item_delete:
                            removeItem(position);
                            Toast.makeText(context, "delete index"+position+" ", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            });
            menu.show();
        }
    }
}
