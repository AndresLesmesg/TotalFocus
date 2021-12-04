package com.andreslesmesg.totalfocus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.model.Note;

import java.util.ArrayList;

public class NoteAdapterRV extends RecyclerView.Adapter<NoteAdapterRV.ViewHolder> {

    private final ArrayList<Note> notes;

    public NoteAdapterRV(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadData(notes.get(position));
        int index = position;

        holder.btn_favorite_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.switchFavorite(notes.get(index));
                holder.btn_favorite_note.setAnimation(holder.fadeOut);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private boolean switchFavoriteNote = false;

        private CardView cv_note;
        private TextView tv_title_note;
        private TextView tv_category_note;
        private ImageButton btn_favorite_note;
        private ImageButton btn_more_note;
        private Animation fadeOut;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_note = itemView.findViewById(R.id.cv_note);
            tv_title_note = itemView.findViewById(R.id.tv_title_note);
            tv_category_note = itemView.findViewById(R.id.tv_category_note);
            btn_favorite_note = itemView.findViewById(R.id.btn_favorite_note);
            btn_more_note = itemView.findViewById(R.id.btn_more_note);

            fadeOut = AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.fade_out);
        }

        public void loadData(Note note) {
            tv_title_note.setText(note.getTitle());
            tv_category_note.setText(note.getCategory());
        }
        public void switchFavorite(Note note){

            btn_favorite_note.setAnimation(fadeOut);
            if(note.getFavorite()){
                note.setFavorite(false);
                btn_favorite_note.setColorFilter(ContextCompat.
                        getColor(itemView.getContext(), R.color.gray_600));
                switchFavoriteNote = false;

            }
            else {
                note.setFavorite(true);
                btn_favorite_note.setColorFilter(ContextCompat.
                        getColor(itemView.getContext(), R.color.cyan_700));
                switchFavoriteNote = true;
            }
        }
    }
}
