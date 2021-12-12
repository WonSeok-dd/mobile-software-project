package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    TextView name_textView;
    Button show_info_button, find_button, plus_button;

    ItemAdapter adapter;
    RecyclerView recyclerView;

    Spinner spinner;
    String[] items_kind= {"","전체","침대","책상","의자"};

    ArrayList<Item> allItemArr = new ArrayList<>();
    ArrayList<Item> bedItemArr = new ArrayList<>();
    ArrayList<Item> deskItemArr = new ArrayList<>();
    ArrayList<Item> chairItemArr = new ArrayList<>();
    ArrayList<Item> findItemArr = new ArrayList<>();
    public static ArrayList<Item> plusItemArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        String name = getIntent().getStringExtra("name");

        //item추가
        addAllitem();

        //리사이클러뷰 설정
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false); 
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(allItemArr);
        recyclerView.setAdapter(adapter);
        
        //로그인 후 설정
        name_textView =findViewById(R.id.name_textView);
        name_textView.setText("\uD83D\uDE00 "+name+"님 환영합니다!");
        
        //고객센터 버튼
        show_info_button = findViewById(R.id.show_info_button);
        show_info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        //검색 버튼
        find_button = findViewById(R.id.find_button);
        find_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //검색한 문자열 얻기
                EditText find_editText = findViewById(R.id.find_editText);
                String find = find_editText.getText().toString();
                find = find.toUpperCase();

                //검색한 문자열 title과 비교
                for(int i=0; i<allItemArr.size(); i++){
                    String it = allItemArr.get(i).getTitle();
                    if(it.contains(find)){
                     findItemArr.add(allItemArr.get(i));
                    }
                }

                adapter = new ItemAdapter(findItemArr);
                recyclerView.setAdapter(adapter);

                spinner.setSelection(0);
                findItemArr = new ArrayList<>();
            }
        });
        
        //장바구니 버튼
        plus_button = findViewById(R.id.plus_button);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowActivity.this, PlusActivity.class);
                intent.putExtra("arr" , plusItemArr);
                startActivity(intent);
            }
        });
        
        //항목선택 스피너
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                ShowActivity.this, android.R.layout.simple_spinner_dropdown_item, items_kind);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){
                    case 0:{
                        break;
                    }
                    case 1:{
                        adapter = new ItemAdapter(allItemArr);
                        recyclerView.setAdapter(adapter);
                        break;
                    }
                    case 2:{
                        adapter = new ItemAdapter(bedItemArr);
                        recyclerView.setAdapter(adapter);
                        break;
                    }

                    case 3:{
                        adapter = new ItemAdapter(deskItemArr);
                        recyclerView.setAdapter(adapter);
                        break;
                    }
                    case 4:{
                        adapter = new ItemAdapter(chairItemArr);
                        recyclerView.setAdapter(adapter);
                        break;
                    }
                    default:{

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    
    public void addAllitem(){
        allItemArr.add(new Item(getString(R.string.image1_url1),getString(R.string.image2_url1),getString(R.string.image3_url1),"레온 프리미엄 빅수납 침대",getString(R.string.description1),"329000원", getString(R.string.url1)));
        allItemArr.add(new Item(getString(R.string.image1_url2),getString(R.string.image2_url2),getString(R.string.image3_url2),"테라 LED 수납 침대",getString(R.string.description2),"379000원", getString(R.string.url2)));
        allItemArr.add(new Item(getString(R.string.image1_url3),getString(R.string.image2_url3),getString(R.string.image3_url3),"하모니 LED 수납 침대",getString(R.string.description3),"209000원", getString(R.string.url3)));
        allItemArr.add(new Item(getString(R.string.image1_url4),getString(R.string.image2_url4),getString(R.string.image3_url4),"RF 뉴비앙 리노서랍 침대",getString(R.string.description4),"319000원", getString(R.string.url4)));
        allItemArr.add(new Item(getString(R.string.image1_url5),getString(R.string.image2_url5),getString(R.string.image3_url5),"알렌 LED 통서랍 수납 침대",getString(R.string.description5),"215000원", getString(R.string.url5)));
        allItemArr.add(new Item(getString(R.string.image1_url6),getString(R.string.image2_url6),getString(R.string.image3_url6),"알베르 원목 서랍 책상",getString(R.string.description6),"199000원", getString(R.string.url6)));
        allItemArr.add(new Item(getString(R.string.image1_url7),getString(R.string.image2_url7),getString(R.string.image3_url7),"오브B 원목 책상",getString(R.string.description7),"229000원", getString(R.string.url7)));
        allItemArr.add(new Item(getString(R.string.image1_url8),getString(R.string.image2_url8),getString(R.string.image3_url8),"씨엘로 하임 원목 책상",getString(R.string.description8),"299000원", getString(R.string.url8)));
        allItemArr.add(new Item(getString(R.string.image1_url9),getString(R.string.image2_url9),getString(R.string.image3_url9),"스틸 좌식 책상",getString(R.string.description9),"66000원", getString(R.string.url9)));
        allItemArr.add(new Item(getString(R.string.image1_url10),getString(R.string.image2_url10),getString(R.string.image3_url10),"플라이 좌식 책상",getString(R.string.description10),"45000원", getString(R.string.url10)));
        allItemArr.add(new Item(getString(R.string.image1_url11),getString(R.string.image2_url11),getString(R.string.image3_url11),"에어 메쉬 의자",getString(R.string.description11),"364000원", getString(R.string.url11)));
        allItemArr.add(new Item(getString(R.string.image1_url12),getString(R.string.image2_url12),getString(R.string.image3_url12),"링고 학생 의자",getString(R.string.description12),"169000원", getString(R.string.url12)));
        allItemArr.add(new Item(getString(R.string.image1_url13),getString(R.string.image2_url13),getString(R.string.image3_url13),"스테이체어 카페 식탁 의자",getString(R.string.description13),"30900원", getString(R.string.url13)));
        allItemArr.add(new Item(getString(R.string.image1_url14),getString(R.string.image2_url14),getString(R.string.image3_url14),"ARENA-X 컴퓨터 게이밍 의자",getString(R.string.description14),"159000원", getString(R.string.url14)));
        allItemArr.add(new Item(getString(R.string.image1_url15),getString(R.string.image2_url15),getString(R.string.image3_url15),"모빌리 디오 바 의자",getString(R.string.description15),"78400원", getString(R.string.url15)));

        bedItemArr.add(new Item(getString(R.string.image1_url1),getString(R.string.image2_url1),getString(R.string.image3_url1),"레온 프리미엄 빅수납 침대",getString(R.string.description1),"329000원", getString(R.string.url1)));
        bedItemArr.add(new Item(getString(R.string.image1_url2),getString(R.string.image2_url2),getString(R.string.image3_url2),"테라 LED 수납 침대",getString(R.string.description2),"379000원", getString(R.string.url2)));
        bedItemArr.add(new Item(getString(R.string.image1_url3),getString(R.string.image2_url3),getString(R.string.image3_url3),"하모니 LED 수납 침대",getString(R.string.description3),"209000원", getString(R.string.url3)));
        bedItemArr.add(new Item(getString(R.string.image1_url4),getString(R.string.image2_url4),getString(R.string.image3_url4),"RF 뉴비앙 리노서랍 침대",getString(R.string.description4),"319000원", getString(R.string.url4)));
        bedItemArr.add(new Item(getString(R.string.image1_url5),getString(R.string.image2_url5),getString(R.string.image3_url5),"알렌 LED 통서랍 수납 침대",getString(R.string.description5),"215000원", getString(R.string.url5)));

        deskItemArr.add(new Item(getString(R.string.image1_url6),getString(R.string.image2_url6),getString(R.string.image3_url6),"알베르 원목 서랍 책상",getString(R.string.description6),"199000원", getString(R.string.url6)));
        deskItemArr.add(new Item(getString(R.string.image1_url7),getString(R.string.image2_url7),getString(R.string.image3_url7),"오브B 원목 책상",getString(R.string.description7),"229000원", getString(R.string.url7)));
        deskItemArr.add(new Item(getString(R.string.image1_url8),getString(R.string.image2_url8),getString(R.string.image3_url8),"씨엘로 하임 원목 책상",getString(R.string.description8),"299000원", getString(R.string.url8)));
        deskItemArr.add(new Item(getString(R.string.image1_url9),getString(R.string.image2_url9),getString(R.string.image3_url9),"스틸 좌식 책상",getString(R.string.description9),"66000원", getString(R.string.url9)));
        deskItemArr.add(new Item(getString(R.string.image1_url10),getString(R.string.image2_url10),getString(R.string.image3_url10),"플라이 좌식 책상",getString(R.string.description10),"45000원", getString(R.string.url10)));

        chairItemArr.add(new Item(getString(R.string.image1_url11),getString(R.string.image2_url11),getString(R.string.image3_url11),"에어 메쉬 의자",getString(R.string.description11),"364000원", getString(R.string.url11)));
        chairItemArr.add(new Item(getString(R.string.image1_url12),getString(R.string.image2_url12),getString(R.string.image3_url12),"링고 학생 의자",getString(R.string.description12),"169000원", getString(R.string.url12)));
        chairItemArr.add(new Item(getString(R.string.image1_url13),getString(R.string.image2_url13),getString(R.string.image3_url13),"스테이체어 카페 식탁 의자",getString(R.string.description13),"30900원", getString(R.string.url13)));
        chairItemArr.add(new Item(getString(R.string.image1_url14),getString(R.string.image2_url14),getString(R.string.image3_url14),"ARENA-X 컴퓨터 게이밍 의자",getString(R.string.description14),"159000원", getString(R.string.url14)));
        chairItemArr.add(new Item(getString(R.string.image1_url15),getString(R.string.image2_url15),getString(R.string.image3_url15),"모빌리 디오 바 의자",getString(R.string.description15),"78400원", getString(R.string.url15)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        plusItemArr  = new ArrayList<>(); //로그아웃시 장바구니초기화
    }
}