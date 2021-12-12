package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {


    Button yes_button, no_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //회원가입 확인 버튼
        yes_button = findViewById(R.id.yes_button);
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson(view);
            }
        });

        //입력정보 초기화 버튼
        no_button = findViewById(R.id.no_button);
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText join_id_editText= findViewById(R.id.join_id_editText);
                join_id_editText.setText("");

                EditText join_password_editText = findViewById(R.id.join_password_editText);
                join_password_editText.setText("");

                EditText join_name_editText = findViewById(R.id.join_name_editText);
                join_name_editText.setText("");
            }
        });
    }

    public void addPerson(View view){

        ContentValues contentValues = new ContentValues();

        EditText editText1 = findViewById(R.id.join_id_editText);
        String id = editText1.getText().toString();
        contentValues.put(MyContentProvider.ID, id);

        EditText editText2 = findViewById(R.id.join_password_editText);
        String password = editText2.getText().toString();
        contentValues.put(MyContentProvider.PASSWORD, password);

        EditText editText3 = findViewById(R.id.join_name_editText);
        String name = editText3.getText().toString();
        contentValues.put(MyContentProvider.NAME, name);

        if((!id.equals("")) && (!password.equals("")) && (!name.equals(""))){ //모든정보입력o

            if(getPerson(id)== null){ //해당정보 없을때
                getContentResolver().insert(MyContentProvider.CONTENT_URI, contentValues);

                Toast.makeText(getBaseContext(), "회원 가입 완료", Toast.LENGTH_SHORT).show();
                finish();
            }else{ //해당정보 있을때
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(JoinActivity.this);
                View dialogview = getLayoutInflater().inflate(R.layout.view_dialog_join2, null);
                alertDialog.setView(dialogview);
                alertDialog.show();
            }

        }else{ //모든정보입력x
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(JoinActivity.this);
            View dialogview = getLayoutInflater().inflate(R.layout.view_dialog_join, null);
            alertDialog.setView(dialogview);
            alertDialog.show();
        }
    }

    public String[] getPerson(String id){
        String[] columns = new String[]{"_id","id","password", "name"};
        String where ="id =? ";
        String[] whereArgs = {id};

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