package com.andreslesmesg.totalfocus.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.andreslesmesg.totalfocus.R;
import com.andreslesmesg.totalfocus.model.Note;
import com.andreslesmesg.totalfocus.model.Note;
import com.andreslesmesg.totalfocus.view.NoteActivity;

import java.util.ArrayList;

public class NoteAdapterRV extends RecyclerView.Adapter<NoteAdapterRV.ViewHolder> {

    private final ArrayList<Note> notes;
    private Activity context;

    public NoteAdapterRV(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_note,null,false);
        context = (Activity) view.getContext();
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

        holder.btn_more_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.showMenu(notes.get(index), index);
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
        private PopupMenu menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Views
            cv_note = itemView.findViewById(R.id.cv_note);
            tv_title_note = itemView.findViewById(R.id.tv_title_note);
            tv_category_note = itemView.findViewById(R.id.tv_category_note);
            btn_favorite_note = itemView.findViewById(R.id.btn_favorite_note);
            btn_more_note = itemView.findViewById(R.id.btn_more_note);
            //Menu
            menu = new PopupMenu(itemView.getContext(), btn_more_note);
            //Animations
            fadeOut = AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.fade_out);
        }

        public void loadData(Note note) {
            tv_title_note.setText(note.getTitle());
            tv_category_note.setText(note.getCategory());
        }

        public void removeItem(int position) {
            notes.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position);
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

        public void showMenu(Note note, int position) {
            menu.getMenu().clear(); //Clear Menu - Fix Generate infinite Menu
            menu.getMenuInflater().inflate(R.menu.menu_resource, menu.getMenu());
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.item_edit:
                            Intent intent = new Intent(itemView.getContext(), NoteActivity.class);
                            intent.putExtra("index", position);
                            intent.putExtra("title", note.getTitle());
                            intent.putExtra("content", note.getContent());
                            intent.putExtra("category", note.getCategory());
                            context.startActivity(intent);
                            break;
                        case R.id.item_delete:
                            removeItem(position);
                            break;
                    }
                    return false;
                }
            });
            menu.show();
        }
    }
}
