package com.example.gatya;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GatyaGatya.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper1(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //テーブル作成
        String userTable = "CREATE TABLE user("+"userid INTEGER PRIMARY KEY,"+"name TEXT)";
        db.execSQL(userTable);
        String collectionTable = "CREATE TABLE collection("+"collection_id INTEGER,"+"collection_name TEXT,"+"rarity TEXT,"+"image_path TEXT,"+"get_flag INTEGER)";
        db.execSQL(collectionTable);
        //userテーブルに初期値を入れておく
        String sqlUserInsert = "INSERT INTO user(userid,name) VALUES(1,'なまえ');";
        db.execSQL(sqlUserInsert);
        //collectionテーブルの内容を挿入
        String sqlCollectionInsert = "INSERT INTO collection(collection_id, collection_name, rarity, get_flag) VALUES "
                + "(1, 'かえる', 'N', 0), "
                + "(2, '赤へる', 'N', 0), "
                + "(3, 'ツチノコ', 'N', 0), "
                + "(4, '竜騎士', 'R', 0), "
                + "(5, 'ダークエルフ', 'R', 0),"
                + "(6, '深きものども', 'R', 0), "
                + "(7, 'アメミット', 'SR', 0), "
                + "(8, '堕天使', 'SR', 0), "
                + "(9, 'ミミック', 'SR', 0), "
                + "(10, 'ドラゴン', 'HR', 0),"
                + "(11, 'クラーケン', 'HR', 0), "
                + "(12, 'リヴァイアサン', 'HR', 0), "
                + "(13, '神', 'UR', 0), "
                + "(14, '魔王', 'UR', 0), "
                + "(15, 'スライム', 'UR', 0);";
        db.execSQL(sqlCollectionInsert);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    }
}
