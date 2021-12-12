package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.relex.circleindicator.CircleIndicator;

public class ItemActivity extends AppCompatActivity {

    ViewPager image_pager;
    CircleIndicator circleIndicator;
    TextView select_item_title, select_item_description, select_item_price,select_item_show_url ,select_item_url;
    String image1_url, image2_url, image3_url, title, description, price, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //ItemAdpter에서 전달된 intent 부가데이터 저장
        image1_url = getIntent().getStringExtra("image1_url");
        image2_url = getIntent().getStringExtra("image2_url");
        image3_url = getIntent().getStringExtra("image3_url");
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        price = getIntent().getStringExtra("price");
        url = getIntent().getStringExtra("url");

        //이미지 뷰페이저 설정
        image_pager = (ViewPager)findViewById(R.id.image_vp);
        image_pager.setOffscreenPageLimit(3);

        ImagePageAdapter imagePageAdapter = new ImagePageAdapter(getSupportFragmentManager());

        Bundle image1_bundle = new Bundle(); //bundle객체로 만들기 - fragment1전달
        ParcelData image1_data = new ParcelData(image1_url);
        image1_bundle.putParcelable("image1_url", image1_data);

        Bundle image2_bundle = new Bundle(); //bundle객체로 만들기 - fragment2전달
        ParcelData image2_data = new ParcelData(image2_url);
        image2_bundle.putParcelable("image2_url",image2_data);

        Bundle image3_bundle = new Bundle(); //bundle객체로 만들기 - fragment3전달
        ParcelData image3_data = new ParcelData(image3_url);
        image3_bundle.putParcelable("image3_url", image3_data);

        ImageFragment1 imageFragment1 = new ImageFragment1();
        imageFragment1.setArguments(image1_bundle);
        imagePageAdapter.additem(imageFragment1); //ViewPagerAdpater에 fragment1추가

        ImageFragment2 imageFragment2 = new ImageFragment2();
        imageFragment2.setArguments(image2_bundle);
        imagePageAdapter.additem(imageFragment2); //ViewPagerAdapter에 fragment2추가

        ImageFragment3 imageFragment3 = new ImageFragment3();
        imageFragment3.setArguments(image3_bundle);
        imagePageAdapter.additem(imageFragment3); //ViewPagerAdapter에 fragment3추가

        image_pager.setAdapter(imagePageAdapter); //ViewPager에 ViewPagerAdapter연결

        //뷰페이저 표시자 설정
        circleIndicator = findViewById(R.id.image_indicator);
        circleIndicator.setViewPager(image_pager);

        //상품상세정보 설정
        select_item_title = findViewById(R.id.select_item_title);
        select_item_title.setText(title);

        select_item_description = findViewById(R.id.select_item_description);
        select_item_description.setText(description);

        select_item_price = findViewById(R.id.select_item_price);
        select_item_price.setText(price);

        select_item_show_url = findViewById(R.id.select_item_show_url);
        select_item_show_url.setText(url);

        select_item_url = findViewById(R.id.select_item_url);
        select_item_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

    }
}