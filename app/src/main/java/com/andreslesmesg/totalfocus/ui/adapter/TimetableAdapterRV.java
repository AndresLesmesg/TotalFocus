package com.andreslesmesg.totalfocus.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.model.Timetable;

import java.util.ArrayList;

public class TimetableAdapterRV extends RecyclerView.Adapter<TimetableAdapterRV.ViewHolder> {

    ArrayList<Timetable> timetables;

    public TimetableAdapterRV(ArrayList<Timetable> timetables) {
        this.timetables = timetables;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_timetable,null,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadData(timetables.get(position));
    }

    @Override
    public int getItemCount() {
        return timetables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_timetable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_timetable = itemView.findViewById(R.id.cv_timetable);
        }

        public void loadData(Timetable timetable) {

        }
    }
}
