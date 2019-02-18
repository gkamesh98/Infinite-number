package com.example.kamesh.a2048;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class Series extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static NumberPicker input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        Button begin = findViewById(R.id.button);

        input = findViewById(R.id.inputnumber);
        input.setMinValue(1);
        input.setMaxValue(100);
        begin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(Integer.parseInt(String.valueOf(input.getValue())) != 0 )
                {
                    Intent play = new Intent(Series.this,MainActivity.class);
                    sendint();
                    startActivity(play);
                }
            }
        });
    }
    public static int sendint()
    {
        input.setValue(input.getValue());
        return Integer.parseInt(String.valueOf(input.getValue()));

    }
}
