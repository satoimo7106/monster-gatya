package com.example.gatya;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    private DatabaseHelper1 _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        _helper = new DatabaseHelper1(StartActivity.this);

        Button btnregister = findViewById(R.id.btStartInputName);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = null;

                try {
                    int userId = 1;

                    EditText tvinput = findViewById(R.id.etStartInputName);
                    String note = tvinput.getText().toString();

                    db = _helper.getWritableDatabase();
                    db.beginTransaction();

                    String selectQuery = "SELECT * FROM user WHERE userid = ?";
                    Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(userId)});
                    if(cursor.getCount() > 0) {
                        String updateQuery = "UPDATE user SET name = ? WHERE userid = ?";
                        db.execSQL(updateQuery,new Object[]{note,userId});
                    }else{
                        String insertQuery = "INSERT INTO user(userid,name)VALUES(?,?)";
                        db.execSQL(insertQuery, new Object[]{userId, note});
                    }
                    db.setTransactionSuccessful();
                    // Log.d("StartActivity", "Transaction successful");
                } catch(Exception e){
                    e.printStackTrace();
                    // Log.e("StartActivity", "Error during transaction", e);
                } finally{
                    if (db != null) {
                        db.endTransaction();
                        db.close();
                    }
                    EditText tvinput = findViewById(R.id.etStartInputName);
                    tvinput.setText("");

                    Button btnregister = findViewById(R.id.btStartInputName);
                    btnregister.setEnabled(true);

                    logDatabaseContents();

                    // Move Intent creation here to execute after database operation
                    Intent intent = new Intent(StartActivity.this, GatyaActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        _helper.close();
        super.onDestroy();
    }

    private void logDatabaseContents() {
        SQLiteDatabase db = _helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        try {
            // (略)
        } finally {
            cursor.close();
        }
    }
}
