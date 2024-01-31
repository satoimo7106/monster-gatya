package com.example.gatya;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import java.util.Random;

public class ResultActivity extends AppCompatActivity {
    private DatabaseHelper1 _helper;
    private MediaPlayer mediaPlayer;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // mediaPlayerの初期化
        mediaPlayer = MediaPlayer.create(this, R.raw.maou_se_system37);

        // DBの初期化
        _helper = new DatabaseHelper1(ResultActivity.this);
        db = _helper.getWritableDatabase();

        TextView tvResultName = null;
        String resultRarity = null;
        String resultName = null;

        tvResultName = findViewById(R.id.tvResultName);
        Button btResultGoGatya = findViewById(R.id.btResultGoGatya);
        Button btResultGoCollection = findViewById(R.id.btResultGoCollection);
        ImageView ivResultImage = findViewById(R.id.ivResultImage);

        // 1~100までの乱数を生成
        int randomRarity = new Random().nextInt(100) + 1;
        // 1~3までの乱数を生成
        int randomItem = new Random().nextInt(3) + 1;

        int resultId = 0;

        // 排出するレア度を決定
        if (randomRarity <= 55) {
            resultRarity = "【N】";
            switch (randomItem) {
                case 1:
                    resultName = "かえる";
                    ivResultImage.setImageResource(R.drawable.n_kaeru);
                    resultId = 1;
                    break;
                case 2:
                    resultName = "赤へる";
                    ivResultImage.setImageResource(R.drawable.n_akaheru);
                    resultId = 2;
                    break;
                case 3:
                    resultName = "ツチノコ";
                    ivResultImage.setImageResource(R.drawable.n_tsuchinoko);
                    resultId = 3;
                    break;
            }
        } else if (randomRarity <= 75) {
            resultRarity = "【R】";
            switch (randomItem) {
                case 1:
                    resultName = "竜騎士";
                    ivResultImage.setImageResource(R.drawable.r_ryuukishi);
                    resultId = 4;
                    break;
                case 2:
                    resultName = "ダークエルフ";
                    ivResultImage.setImageResource(R.drawable.r_dark_eruhu);
                    resultId = 5;
                    break;
                case 3:
                    resultName = "深きものども";
                    ivResultImage.setImageResource(R.drawable.r_hukakimonodomo);
                    resultId = 6;
                    break;
            }
        } else if (randomRarity <= 90) {
            resultRarity = "【SR】";
            switch (randomItem) {
                case 1:
                    resultName = "アメミット";
                    ivResultImage.setImageResource(R.drawable.sr_ammit);
                    resultId = 7;
                    break;
                case 2:
                    resultName = "堕天使";
                    ivResultImage.setImageResource(R.drawable.sr_datenshi);
                    resultId = 8;
                    break;
                case 3:
                    resultName = "ミミック";
                    ivResultImage.setImageResource(R.drawable.sr_mimic);
                    resultId = 9;
                    break;
            }
        } else if (randomRarity <= 98) {
            resultRarity = "【HR】";
            switch (randomItem) {
                case 1:
                    resultName = "ドラゴン";
                    ivResultImage.setImageResource(R.drawable.hr_dragon);
                    resultId = 10;
                    break;
                case 2:
                    resultName = "クラーケン";
                    ivResultImage.setImageResource(R.drawable.hr_kraken);
                    resultId = 11;
                    break;
                case 3:
                    resultName = "リヴァイアサン";
                    ivResultImage.setImageResource(R.drawable.hr_leviathan);
                    resultId = 12;
                    break;
            }
        } else {
            resultRarity = "【UR】";
            switch (randomItem) {
                case 1:
                    resultName = "神";
                    ivResultImage.setImageResource(R.drawable.ur_internet_god);
                    resultId = 13;
                    break;
                case 2:
                    resultName = "魔王";
                    ivResultImage.setImageResource(R.drawable.ur_maou);
                    resultId = 14;
                    break;
                case 3:
                    resultName = "スライム";
                    ivResultImage.setImageResource(R.drawable.ur_slime);
                    resultId = 15;
                    break;
            }
        }

        // 結果のテキストを表示するTextViewに反映
        tvResultName.setText(resultRarity + " " + resultName);

        // 出たキャラのフラグをオンにする処理（DB）
        String sqlCollectionFlag = "UPDATE collection SET get_flag=1 WHERE collection_id=?";
        SQLiteStatement updateStatement = db.compileStatement(sqlCollectionFlag);
        updateStatement.bindLong(1, resultId);
        updateStatement.execute();

        playMusic();

        // 「もう１回回す」ボタンを押した時、ガチャ画面に移動
        btResultGoGatya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 現在のアクティビティを再読み込み
                recreate();
            }
        });

        // 「コレクションへ」ボタンを押した時、コレクション画面に移動
        btResultGoCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void playMusic() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setVolume(1.0f,1.0f);
                        mp.start();
                    }
                });
                mediaPlayer.prepareAsync();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
