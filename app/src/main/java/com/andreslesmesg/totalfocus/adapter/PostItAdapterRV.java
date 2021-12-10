package com.andreslesmesg.totalfocus.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
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
import com.andreslesmesg.totalfocus.controller.PostItController;
import com.andreslesmesg.totalfocus.model.PostIt;
import com.andreslesmesg.totalfocus.view.NoteActivity;

import java.util.ArrayList;

public class PostItAdapterRV extends RecyclerView.Adapter<PostItAdapterRV.ViewHolder> {

    private final ArrayList<PostIt> postIts;
    private Activity context;

    public PostItAdapterRV(ArrayList<PostIt> postIts) {
        this.postIts = postIts;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postit,null,false);
        context = (Activity) view.getContext();
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {

        holder.loadData(postIts.get(position));

        holder.tv_content_post.setOnClickListener(v -> {
            holder.switchContent(postIts.get(position));
            holder.tv_content_post.setAnimation(holder.switchCard);
        });

        holder.btn_favorite_post.setOnClickListener(v -> {
            holder.switchFavorite(postIts.get(position));
            holder.btn_favorite_post.setAnimation(holder.fadeOut);
        });

        holder.btn_more_post.setOnClickListener(v -> holder.showMenu(position));
    }

    @Override
    public int getItemCount() {
        return postIts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_content_post;
        private final ImageButton btn_favorite_post, btn_more_post;
        private final Animation switchCard;
        private final Animation fadeOut;
        private final PopupMenu menu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CardView cv_post = itemView.findViewById(R.id.cv_post);
            tv_content_post = itemView.findViewById(R.id.tv_content_post);
            btn_favorite_post = itemView.findViewById(R.id.btn_favorite_post);
            btn_more_post = itemView.findViewById(R.id.btn_more_post);

            menu = new PopupMenu(itemView.getContext(), btn_more_post);

            switchCard = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.switch_card_content);
            fadeOut = AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.fade_out);
        }

        public void loadData(PostIt postIt) {
            tv_content_post.setText(postIt.getTitle());
        }

        public void removeItem(int position) {

            postIts.remove(position);
            PostItController.deletePostIt(position);

            notifyItemRemoved(position);
            notifyItemChanged(position, postIts.size());

        }

        @SuppressLint("SetTextI18n")
        public void switchContent(PostIt postIt){
            btn_favorite_post.setAnimation(switchCard);
            if(postIt.getOrientation()){
                postIt.setOrientation(false);
                tv_content_post.setTextColor(itemView.getContext().
                        getResources().getColor(R.color.gray_600));

                tv_content_post.setTextSize(itemView.getContext().
                        getResources().getDimension(R.dimen.font_size_medium));
            }else {
                postIt.setOrientation(true);
                tv_content_post.setTextColor(itemView.getContext().
                        getResources().getColor(R.color.gray_800));
                tv_content_post.setTextSize(itemView.getContext().
                        getResources().getDimension(R.dimen.font_size_small));
            }
            tv_content_post.setText(postIt.getContent());
        }

        public void switchFavorite(PostIt postIt){

            btn_favorite_post.setAnimation(fadeOut);
            if(postIt.getFavorite()){
                postIt.setFavorite(false);
                btn_favorite_post.setColorFilter(ContextCompat.
                        getColor(itemView.getContext(), R.color.gray_600),
                        android.graphics.PorterDuff.Mode.SRC_IN);
            }else {
                postIt.setFavorite(true);
                btn_favorite_post.setColorFilter(ContextCompat.
                        getColor(itemView.getContext(), R.color.cyan_700),
                        android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }

        @SuppressLint("NonConstantResourceId")
        public void showMenu(int position) {
            menu.getMenu().clear(); //Clear Menu - Fix Generate infinite Menu
            menu.getMenuInflater().inflate(R.menu.menu_resource, menu.getMenu());
            menu.setOnMenuItemClickListener(item -> {

                switch (item.getItemId()){
                    case R.id.item_edit:
                        Intent intent = new Intent(itemView.getContext(), NoteActivity.class);

                        intent.putExtra("index", position);
                        context.startActivity(intent);
                        break;

                    case R.id.item_delete:
                        removeItem(position);
                        break;
                }
                return false;
            });
            menu.show();
            notifyItemChanged(position);
        }
    }
}
