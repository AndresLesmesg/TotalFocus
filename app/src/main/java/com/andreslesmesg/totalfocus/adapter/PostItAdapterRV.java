package com.andreslesmesg.totalfocus.adapter;

import android.annotation.SuppressLint;
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
import com.andreslesmesg.totalfocus.model.PostIt;

import java.util.ArrayList;

public class PostItAdapterRV extends RecyclerView.Adapter<PostItAdapterRV.ViewHolder> {

    private final ArrayList<PostIt> postIts;

    public PostItAdapterRV(ArrayList<PostIt> postIts) {
        this.postIts = postIts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postit,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {

        holder.loadData(postIts.get(position));
        int index = position;

        holder.tv_content_post.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                holder.switchContent(postIts.get(index));
                holder.tv_content_post.setAnimation(holder.switchCard);
            }
        });

        holder.btn_favorite_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.switchFavorite(postIts.get(index));
                holder.btn_favorite_post.setAnimation(holder.fadeOut);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postIts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private boolean switchFavoritePost;

        private CardView cv_post;
        private TextView tv_content_post;
        private ImageButton btn_favorite_post;
        private ImageButton btn_more_post;
        private Animation switchCard, fadeOut;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            switchFavoritePost = false;

            cv_post = itemView.findViewById(R.id.cv_post);
            tv_content_post = itemView.findViewById(R.id.tv_content_post);
            btn_favorite_post = itemView.findViewById(R.id.btn_favorite_post);
            btn_more_post = itemView.findViewById(R.id.btn_more_post);

            switchCard = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.switch_card_content);
            fadeOut = AnimationUtils.loadAnimation(itemView.getContext(), android.R.anim.fade_out);
        }

        public void loadData(PostIt postIt) {
            tv_content_post.setText(postIt.getTitle());
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
                switchFavoritePost = false;
            }else {
                postIt.setFavorite(true);
                btn_favorite_post.setColorFilter(ContextCompat.
                        getColor(itemView.getContext(), R.color.cyan_700),
                        android.graphics.PorterDuff.Mode.SRC_IN);
                switchFavoritePost = true;
            }
        }
    }
}
