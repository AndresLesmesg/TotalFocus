package com.andreslesmesg.totalfocus.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.databinding.FragmentMainBinding;
import com.andreslesmesg.totalfocus.model.Course;
import com.andreslesmesg.totalfocus.model.Note;
import com.andreslesmesg.totalfocus.model.PostIt;
import com.andreslesmesg.totalfocus.model.Timetable;
import com.andreslesmesg.totalfocus.ui.adapter.CourseAdapterRV;
import com.andreslesmesg.totalfocus.ui.adapter.NoteAdapterRV;
import com.andreslesmesg.totalfocus.ui.adapter.PostItAdapterRV;
import com.andreslesmesg.totalfocus.ui.adapter.TimetableAdapterRV;

import java.util.ArrayList;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
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

        pageViewModel.getIndex().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
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
            }
        });
        //final TextView textView = binding.sectionLabel;
        //pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }

    private ArrayList<Course> getCourses() {
        ArrayList<Course> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Course("Curso # "+(i+1), (int) ((Math.random() * 4))));
        }
        return data;
    }

    private ArrayList<Note> getNotes() {
        ArrayList<Note> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Note("Nota # "+(i+1), (int) ((Math.random() * 4))));
        }
        return data;
    }

    private ArrayList<PostIt> getPostIts() {

        ArrayList<PostIt> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new PostIt("Tilulo # "+(i+1), "Contenido # "+(i+1)));
        }
        return data;
    }

    private ArrayList<Timetable> getTimetables() {
        ArrayList<Timetable> data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            data.add(new Timetable("Horario # "+(i+1)));
        }
        return data;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}