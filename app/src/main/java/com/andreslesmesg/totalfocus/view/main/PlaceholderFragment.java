package com.andreslesmesg.totalfocus.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.adapter.CourseAdapterRV;
import com.andreslesmesg.totalfocus.adapter.NoteAdapterRV;
import com.andreslesmesg.totalfocus.adapter.PostItAdapterRV;
import com.andreslesmesg.totalfocus.adapter.TimetableAdapterRV;
import com.andreslesmesg.totalfocus.controller.CourseController;
import com.andreslesmesg.totalfocus.controller.NoteController;
import com.andreslesmesg.totalfocus.controller.PostItController;
import com.andreslesmesg.totalfocus.controller.TimetableController;
import com.andreslesmesg.totalfocus.databinding.FragmentMainBinding;
import com.andreslesmesg.totalfocus.model.Course;
import com.andreslesmesg.totalfocus.model.Note;
import com.andreslesmesg.totalfocus.model.PostIt;
import com.andreslesmesg.totalfocus.model.Timetable;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding binding;
    private RecyclerView rv_container;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rv_container = root.findViewById(R.id.rv_container);
        rv_container.setLayoutManager(new LinearLayoutManager(getContext()));

        pageViewModel.getIndex().observe(getViewLifecycleOwner(), integer -> {
            switch (integer){
                case 1:
                    CourseAdapterRV courseAdapterRV = new CourseAdapterRV(getCourses());
                    rv_container.setAdapter(courseAdapterRV);
                    break;
                case 2:
                    NoteAdapterRV noteAdapterRV = new NoteAdapterRV(getNotes());
                    rv_container.setAdapter(noteAdapterRV);
                    break;
                case 3:
                    PostItAdapterRV postItAdapterRV = new PostItAdapterRV(getPostIts());
                    rv_container.setAdapter(postItAdapterRV);
                    break;
                case 4:
                    TimetableAdapterRV timetableAdapterRV = new TimetableAdapterRV(getTimetables());
                    rv_container.setAdapter(timetableAdapterRV);
                    break;
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        pageViewModel.getIndex().observe(getViewLifecycleOwner(), integer -> {
            switch (integer) {
                case 1:
                    CourseAdapterRV courseAdapterRV = new CourseAdapterRV(CourseController.getCourses());
                    rv_container.setAdapter(courseAdapterRV);
                    break;
                case 2:
                    NoteAdapterRV noteAdapterRV = new NoteAdapterRV(getNotes());
                    rv_container.setAdapter(noteAdapterRV);
                    break;
                case 3:
                    PostItAdapterRV postItAdapterRV = new PostItAdapterRV(getPostIts());
                    rv_container.setAdapter(postItAdapterRV);
                    break;
                case 4:
                    TimetableAdapterRV timetableAdapterRV = new TimetableAdapterRV(getTimetables());
                    rv_container.setAdapter(timetableAdapterRV);
                    break;
            }
        });
    }

    private static ArrayList<Course> getCourses() {
        CourseController.initCourse();
        return CourseController.getCourses();
    }

    private static ArrayList<Note> getNotes() {
        NoteController.initNote();
        return  NoteController.getNotes();
    }

    private static ArrayList<PostIt> getPostIts() {
        PostItController.initPostIt();
        return PostItController.getPostIts();
    }

    private static ArrayList<Timetable> getTimetables() {
        TimetableController.initTimetable();
        return TimetableController.getTimetables();
    }


}