package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlusItemAdapter extends RecyclerView.Adapter<PlusItemAdapter.ViewHolder> {

    private int lastPosition = -1; //애니메이션에서 사용
    ArrayList<Item> items = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView plus_item_title;

        //생성자
        public ViewHolder(View itemView) {
            super(itemView);
            plus_item_title = itemView.findViewById(R.id.plus_item_title);
        }
    }

    PlusItemAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public PlusItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.plus_item_view, viewGroup, false);
        return new PlusItemAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlusItemAdapter.ViewHolder viewHolder, int position) {

        Item item = items.get(position);
        viewHolder.plus_item_title.setText("\""+item.getTitle()+"\"" +" 를 장바구니에 추가하셨습니다.");

        setAnimation(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    //BindViewHolder에서 애니메이션 사용
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}