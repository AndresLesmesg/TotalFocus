package com.andreslesmesg.totalfocus.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.model.Note;

import java.util.ArrayList;

public class NoteAdapterRV extends RecyclerView.Adapter<NoteAdapterRV.ViewHolder> {

    ArrayList<Note> notes;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadData(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_note;
        TextView tv_title_note;
        TextView tv_category_note;
        ImageButton btn_favorite_note;
        ImageButton btn_more_note;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_note = itemView.findViewById(R.id.cv_note);
            tv_title_note = itemView.findViewById(R.id.tv_title_note);
            tv_category_note = itemView.findViewById(R.id.tv_category_note);
            btn_favorite_note = itemView.findViewById(R.id.btn_favorite_note);
            btn_more_note = itemView.findViewById(R.id.btn_more_note);
        }

        public void loadData(Note note) {
            tv_title_note.setText(note.getTitle());
            tv_category_note.setText(note.getCategory());
        }
    }
}
