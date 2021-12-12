package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageFragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_image2, container, false);
        Bundle image2_bundle = getArguments();
        ParcelData image2_data = image2_bundle.getParcelable("image2_url");
        String image2_url = image2_data.url;

        ImageView imageView = rootView.findViewById(R.id.fragment_imageView2);
        Glide.with(rootView.getContext())
                .load(image2_url)
                .error(R.drawable.ic_baseline_wallpaper_24)
                .into(imageView);

        return rootView;
    }
}