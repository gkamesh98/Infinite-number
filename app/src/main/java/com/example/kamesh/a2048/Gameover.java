package com.example.kamesh.a2048;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Gameover extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        Button home = findViewById(R.id.button2);
        TextView score = findViewById(R.id.textView3);
        TextView highscore = findViewById(R.id.textView4);
        score.setText("Score: "+MainActivity.Score());
        highscore.setText("High Score: "+MainActivity.HighScore());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fisrt = new Intent(Gameover.this,Series.class);
                startActivity(fisrt);
                finish();
            }
        });

    }
}
