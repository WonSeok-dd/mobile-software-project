package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PlusActivity extends AppCompatActivity {

    PlusItemAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        //showActivity에서 전달된 intent의 부가데이터 배열저장
        ArrayList<Item> itemList = (ArrayList<Item>)getIntent().getSerializableExtra("arr");

        //리사이클러뷰 설정
        recyclerView = findViewById(R.id.plus_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlusItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

    }
}