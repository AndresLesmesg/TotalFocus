package com.andreslesmesg.totalfocus.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.model.Timetable;
import com.andreslesmesg.totalfocus.view.NoteActivity;
import com.andreslesmesg.totalfocus.view.TimetableActivity;

import java.util.ArrayList;

public class TimetableAdapterRV extends RecyclerView.Adapter<TimetableAdapterRV.ViewHolder> {

    private final ArrayList<Timetable> timetables;
    private Activity context;

    public TimetableAdapterRV(ArrayList<Timetable> timetables) {
        this.timetables = timetables;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_timetable,null,false);
        context = (Activity) view.getContext();
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadData(timetables.get(position));

        holder.sw_timetable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(context, "Notificación Activada",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context, "Notificación Desactivada",Toast.LENGTH_LONG).show();
                }
            }
        });

        holder.btn_more_timetable.setOnClickListener(v -> holder.showMenu(timetables.get(position), position));
    }

    @Override
    public int getItemCount() {
        return timetables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_timetable;
        ImageButton btn_more_timetable;
        Switch sw_timetable;
        TextView tv_title_timetable;
        TextView tv_days_timetable;
        TextView tv_hours_timetable;
        private final PopupMenu menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_timetable = itemView.findViewById(R.id.cv_timetable);
            btn_more_timetable = itemView.findViewById(R.id.btn_more_timetable);
            tv_title_timetable = itemView.findViewById(R.id.tv_title_timetable);
            tv_days_timetable = itemView.findViewById(R.id.tv_days_timetable);
            tv_hours_timetable = itemView.findViewById(R.id.tv_hours_timetable);
            sw_timetable = itemView.findViewById(R.id.sw_timetable);

            menu = new PopupMenu(itemView.getContext(), btn_more_timetable);
        }

        public void loadData(Timetable timetable) {
            tv_title_timetable.setText(timetable.getTitle());
            tv_days_timetable.setText(timetable.getDays());
            tv_hours_timetable.setText(timetable.getAlarmHour());
        }

        public void removeItem(int position) {
            timetables.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position, timetables.size());
        }

        @SuppressLint("NonConstantResourceId")
        public void showMenu(Timetable timetable, int position) {
            menu.getMenu().clear(); //Clear Menu - Fix Generate infinite Menu
            menu.getMenuInflater().inflate(R.menu.menu_resource, menu.getMenu());
            menu.setOnMenuItemClickListener(item -> {

                switch (item.getItemId()){
                    case R.id.item_edit:
                        Intent intent = new Intent(itemView.getContext(), TimetableActivity.class);

                        intent.putExtra("index", position);
                        intent.putExtra("title", timetable.getTitle());
                        intent.putExtra("minutes", timetable.getMin());
                        intent.putExtra("hour", timetable.getHour());
                        intent.putExtra("days", timetable.getEnableDays());

                        context.startActivity(intent);
                        break;

                    case R.id.item_delete:
                        removeItem(position);
                        break;
                }
                return false;
            });
            menu.show();
        }
    }
}
