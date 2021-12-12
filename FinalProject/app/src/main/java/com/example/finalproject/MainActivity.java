package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login_button, join_button, info_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //로그인 버튼
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                EditText id_editText = findViewById(R.id.id_editText);
                String id = id_editText.getText().toString();
                
                EditText password_editText = findViewById(R.id.password_editText);
                String password = password_editText.getText().toString();

                if((!id.equals("")) && (!password.equals(""))){ //모든정보입력o
                    if(getPerson(id,password) !=null){ //정보일치o
                        String[] arr = getPerson(id,password);
                        Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                        intent.putExtra("name",arr[2]);
                        startActivity(intent);
                    }else{ //정보일치x
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        View dialogview = getLayoutInflater().inflate(R.layout.view_dialog_login2, null);
                        alertDialog.setView(dialogview);
                        alertDialog.show();
                    }
                }else{ //모든정보입력x
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    View dialogview = getLayoutInflater().inflate(R.layout.view_dialog_login1, null);
                    alertDialog.setView(dialogview);
                    alertDialog.show();
                }
            }
        });

        //회원가입 버튼
        join_button = findViewById(R.id.join_button);
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        //고객센터 버튼
        info_button = findViewById(R.id.info_button);
        info_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    public String[] getPerson(String id , String password){
        String[] columns = new String[]{"_id","id","password", "name"};
        String where ="id =? AND password=?";
        String[] whereArgs = {id,password};

        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, where ,
                whereArgs, null, null);

        if(c!=null){
            while(c.moveToNext()){
                String[] arr = new String[3];
                String id1 = c.getString(1);
                String password1 = c.getString(2);
                String name1 = c.getString(3);
                arr[0]= id1;
                arr[1] = password1;
                arr[2] = name1;
                return arr;
            }
            c.close();
        }

        return null;
    }
}