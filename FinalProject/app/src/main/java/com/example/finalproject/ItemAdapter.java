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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private int lastPosition = -1; //애니메이션에서 사용
    ArrayList<Item> items = new ArrayList<>();


    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView Item_image;
        TextView Item_title, Item_price;
        Button Item_button;

        //생성자
        public ViewHolder(View itemView) {
            super(itemView);

            Item_image = itemView.findViewById(R.id.item_image);
            Item_title = itemView.findViewById(R.id.item_title);
            Item_price = itemView.findViewById(R.id.item_price);
            Item_button = itemView.findViewById(R.id.item_plus_button);

        }
    }

    ItemAdapter(ArrayList<Item> items){
        this.items = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Item item = items.get(position);

        Glide.with(viewHolder.itemView.getContext ())
                .load(item.image1_url)
                .error(R.drawable.ic_baseline_wallpaper_24)
                .into(viewHolder.Item_image);
        viewHolder.Item_title.setText(item.getTitle());
        viewHolder.Item_price.setText(item.getPrice());

        viewHolder.Item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("image1_url", item.getImage1_url());
                intent.putExtra("image2_url",item.getImage2_url());
                intent.putExtra("image3_url",item.getImage3_url());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("description",item.getDescription());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("url",item.getUrl());
                context.startActivity(intent);
            }
        });

        viewHolder.Item_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("image1_url", item.getImage1_url());
                intent.putExtra("image2_url",item.getImage2_url());
                intent.putExtra("image3_url",item.getImage3_url());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("description",item.getDescription());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("url",item.getUrl());
                context.startActivity(intent);
            }
        });

        viewHolder.Item_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("image1_url", item.getImage1_url());
                intent.putExtra("image2_url",item.getImage2_url());
                intent.putExtra("image3_url",item.getImage3_url());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("description",item.getDescription());
                intent.putExtra("price",item.getPrice());
                intent.putExtra("url",item.getUrl());
                context.startActivity(intent);
            }
        });
    
        viewHolder.Item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                ShowActivity.plusItemArr.add(item);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogview = layoutInflater.inflate(R.layout.view_dialog_plus, null);
                alertDialog.setView(dialogview);
                alertDialog.show();
            }
        });
        
        setAnimation(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    //BindViewHolder에서 애니메이션 사용
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}