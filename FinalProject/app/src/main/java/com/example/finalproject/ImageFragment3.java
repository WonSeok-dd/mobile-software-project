package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageFragment3 extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_image3, container, false);
        Bundle image3_bundle = getArguments();
        ParcelData image3_data = image3_bundle.getParcelable("image3_url");
        String image3_url = image3_data.url;

        ImageView imageView = rootView.findViewById(R.id.fragment_imageView3);
        Glide.with(rootView.getContext())
                .load(image3_url)
                .error(R.drawable.ic_baseline_wallpaper_24)
                .into(imageView);

        return rootView;
    }
}