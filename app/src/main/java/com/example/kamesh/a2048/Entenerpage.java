package com.example.kamesh.a2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entenerpage extends AppCompatActivity {

    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entenerpage);
        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play2 = new Intent(Entenerpage.this,Series.class);
                startActivity(play2);
            }
        });
    }

}
