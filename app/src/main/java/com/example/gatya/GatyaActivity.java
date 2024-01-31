package com.example.gatya;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gatya.DatabaseHelper1;
import com.example.gatya.R;

public class GatyaActivity extends AppCompatActivity{
    private int userid = -1;
    private String name = "";


    private DatabaseHelper1 _helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatya);
        _helper = new DatabaseHelper1(GatyaActivity.this);

        TextView tvname2 = findViewById(R.id.tvGatyaTitle);

        //DBから名前を取り出して表示
        SQLiteDatabase db = _helper.getReadableDatabase();
        String sql = "SELECT name FROM user WHERE userid = 1";

        Cursor cursor = db.rawQuery(sql,null);

        String note = "";
        while (cursor.moveToNext()){
            int idxNote = cursor.getColumnIndex("name");
            note = cursor.getString(idxNote);
        }
        cursor.close();

        tvname2.setText("ようこそ "+note+"さん！");

        Button btGatyaGoResult=findViewById(R.id.btGatyaGoResult);
        Button btGatyaGoCollection=findViewById(R.id.btGatyaGoCollection);

        //「回す」ボタンを押した時、ガチャ結果画面に移動
        btGatyaGoResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GatyaActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        //「コレクションへ」ボタンを押した時、コレクション画面に移動
        btGatyaGoCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GatyaActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
    }




    @Override
    protected void onDestroy(){
        _helper.close();
        super.onDestroy();
    }

//    public void loadAndDisplayData(){
//
//    }
}