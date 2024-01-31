package com.example.gatya;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CollectionActivity extends AppCompatActivity {
    private DatabaseHelper1 _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        _helper=new DatabaseHelper1(CollectionActivity.this);
        SQLiteDatabase db=_helper.getWritableDatabase();


//名前を表示
        TextView tvname2 = findViewById(R.id.tvCollectionTitle);
        String sqlName = "SELECT name FROM user WHERE userid = 1";

        Cursor cursorName = db.rawQuery(sqlName,null);

        String note = "";
        while (cursorName.moveToNext()){
            int idxNote = cursorName.getColumnIndex("name");
            note = cursorName.getString(idxNote);
        }
        cursorName.close();

        tvname2.setText(note+"さんのコレクション");

//コレクション処理

        //TextView、ImageViewの紐づけ
        TextView tvCollection1=findViewById(R.id.tvCollection1);
        ImageView ivCollection1=findViewById(R.id.ivCollection1);

        TextView tvCollection2=findViewById(R.id.tvCollection2);
        ImageView ivCollection2=findViewById(R.id.ivCollection2);

        TextView tvCollection3=findViewById(R.id.tvCollection3);
        ImageView ivCollection3=findViewById(R.id.ivCollection3);

        TextView tvCollection4=findViewById(R.id.tvCollection4);
        ImageView ivCollection4=findViewById(R.id.ivCollection4);

        TextView tvCollection5=findViewById(R.id.tvCollection5);
        ImageView ivCollection5=findViewById(R.id.ivCollection5);

        TextView tvCollection6=findViewById(R.id.tvCollection6);
        ImageView ivCollection6=findViewById(R.id.ivCollection6);

        TextView tvCollection7=findViewById(R.id.tvCollection7);
        ImageView ivCollection7=findViewById(R.id.ivCollection7);

        TextView tvCollection8=findViewById(R.id.tvCollection8);
        ImageView ivCollection8=findViewById(R.id.ivCollection8);

        TextView tvCollection9=findViewById(R.id.tvCollection9);
        ImageView ivCollection9=findViewById(R.id.ivCollection9);

        TextView tvCollection10=findViewById(R.id.tvCollection10);
        ImageView ivCollection10=findViewById(R.id.ivCollection10);

        TextView tvCollection11=findViewById(R.id.tvCollection11);
        ImageView ivCollection11=findViewById(R.id.ivCollection11);

        TextView tvCollection12=findViewById(R.id.tvCollection12);
        ImageView ivCollection12=findViewById(R.id.ivCollection12);

        TextView tvCollection13=findViewById(R.id.tvCollection13);
        ImageView ivCollection13=findViewById(R.id.ivCollection13);

        TextView tvCollection14=findViewById(R.id.tvCollection14);
        ImageView ivCollection14=findViewById(R.id.ivCollection14);

        TextView tvCollection15=findViewById(R.id.tvCollection15);
        ImageView ivCollection15=findViewById(R.id.ivCollection15);

        Button btCollectionGoGatya=findViewById(R.id.btCollectionGoGatya);


        //DBから値を取得
        String sql="SELECT collection_id,collection_name,rarity,get_flag FROM collection;";
        Cursor cursor=db.rawQuery(sql,null);

        int idxId=cursor.getColumnIndex("collection_id");
        int idxName=cursor.getColumnIndex("collection_name");
        int idxRarity=cursor.getColumnIndex("rarity");
//        int idxImagePath=cursor.getColumnIndex("image_path");
        int idxFlag=cursor.getColumnIndex("get_flag");

        while (cursor.moveToNext()){
            int collectionID=cursor.getInt(idxId);
            String collectionName=cursor.getString(idxName);
            String rarity=cursor.getString(idxRarity);
//            String imagePath=cursor.getString(idxImagePath);
            int flag=cursor.getInt(idxFlag);
            if (flag==1) {
                switch (collectionID) {
                    case 1:
                        ivCollection1.setImageResource(R.drawable.n_kaeru);
                        tvCollection1.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 2:
                        ivCollection2.setImageResource(R.drawable.n_akaheru);
                        tvCollection2.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 3:
                        ivCollection3.setImageResource(R.drawable.n_tsuchinoko);
                        tvCollection3.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 4:
                        ivCollection4.setImageResource(R.drawable.r_ryuukishi);
                        tvCollection4.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 5:
                        ivCollection5.setImageResource(R.drawable.r_dark_eruhu);
                        tvCollection5.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 6:
                        ivCollection6.setImageResource(R.drawable.r_hukakimonodomo);
                        tvCollection6.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 7:
                        ivCollection7.setImageResource(R.drawable.sr_ammit);
                        tvCollection7.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 8:
                        ivCollection8.setImageResource(R.drawable.sr_datenshi);
                        tvCollection8.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 9:
                        ivCollection9.setImageResource(R.drawable.sr_mimic);
                        tvCollection9.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 10:
                        ivCollection10.setImageResource(R.drawable.hr_dragon);
                        tvCollection10.setText("【" + rarity + "】 " + collectionName);
                        break;

                    case 11:
                        ivCollection11.setImageResource(R.drawable.hr_kraken);
                        tvCollection11.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 12:
                        ivCollection12.setImageResource(R.drawable.hr_leviathan);
                        tvCollection12.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 13:
                        ivCollection13.setImageResource(R.drawable.ur_internet_god);
                        tvCollection13.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 14:
                        ivCollection14.setImageResource(R.drawable.ur_maou);
                        tvCollection14.setText("【" + rarity + "】 " + collectionName);
                        break;
                    case 15:
                        ivCollection15.setImageResource(R.drawable.ur_slime);
                        tvCollection15.setText("【" + rarity + "】 " + collectionName);
                        break;



                }
            }
        }
        //        「ガチャへ」ボタンを押した時、ガチャ画面に移動
        btCollectionGoGatya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, GatyaActivity.class);
                startActivity(intent);
            }
        });

    }





    @Override
    protected void onDestroy(){
        _helper.close();
        super.onDestroy();
    }
}
