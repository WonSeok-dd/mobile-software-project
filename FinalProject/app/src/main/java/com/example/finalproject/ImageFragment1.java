package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageFragment1 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_image1, container, false);
        Bundle image1_bundle = getArguments();
        ParcelData image1_data = image1_bundle.getParcelable("image1_url");
        String image1_url = image1_data.url;

        ImageView imageView = rootView.findViewById(R.id.fragment_imageView1);
        Glide.with(rootView.getContext())
                .load(image1_url)
                .error(R.drawable.ic_baseline_wallpaper_24)
                .into(imageView);

        return rootView;
    }
}