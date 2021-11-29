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
import com.andreslesmesg.totalfocus.model.PostIt;

import java.util.ArrayList;

public class PostItAdapterRV extends RecyclerView.Adapter<PostItAdapterRV.ViewHolder> {

    ArrayList<PostIt> postIts;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postit,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.loadData(postIts.get(position));
    }

    @Override
    public int getItemCount() {
        return postIts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv_post;
        TextView tv_content_post;
        ImageButton btn_favorite_post;
        ImageButton btn_more_post;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_post = itemView.findViewById(R.id.cv_post);
            tv_content_post = itemView.findViewById(R.id.tv_content_post);
            btn_favorite_post = itemView.findViewById(R.id.btn_favorite_post);
            btn_more_post = itemView.findViewById(R.id.btn_more_post);
        }

        public void loadData(PostIt postIt) {
            tv_content_post.setText(postIt.getContent());
        }
    }
}
