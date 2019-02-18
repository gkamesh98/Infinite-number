package com.example.kamesh.a2048;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    GestureDetectorCompat detector;
    TextView number[][] = new TextView[3][3];
    @SuppressLint("StaticFieldLeak")
    static TextView score ;
    int action;
    int a[] = new int[5];
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    @SuppressLint("StaticFieldLeak")
    static TextView highscore;
    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        detector = new GestureDetectorCompat(this,this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting = getSharedPreferences("Game_Data", Context.MODE_PRIVATE);
        editor = setting.edit();

        number[0][0] = findViewById(R.id.number1);
        number[0][1] = findViewById(R.id.number2);
        number[0][2] = findViewById(R.id.number3);
        number[1][0] = findViewById(R.id.number4);
        number[1][1] = findViewById(R.id.number5);
        number[1][2] = findViewById(R.id.number6);
        number[2][0] = findViewById(R.id.number7);
        number[2][1] = findViewById(R.id.number8);
        number[2][2] = findViewById(R.id.number9);
        score = findViewById(R.id.scoreValue);
        highscore = findViewById(R.id.highscoreValue);
        action = Series.sendint();
        a[0] = action;
        a[1] = action;
        a[2] = action;
        a[3] = action;
        a[4] = action*2;
        Random rand = new Random();
        number[rand.nextInt(3)][rand.nextInt(3)].setText(String.valueOf(action));
        highscore.setText(Integer.toString(setting.getInt("High_Score"+action,0)));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX()-e2.getX()>100)
        {
            Left();
        }
        else if(e1.getX()-e2.getX()<-100)
        {
            Right();
        }
        else if(e1.getY()-e2.getY()>100)
        {
            UP();
        }
        else if(e1.getY()-e2.getY()<-100)
        {
            Down();
        }

        return false;
    }
    @SuppressLint("SetTextI18n")
    private void Left() {
        int space = 0;
        for(int i=0;i<3;i++) {
            if (number[i][0].getText() == "" && (number[i][1].getText()!="" || number[i][2].getText()!=""))
            {
                space++;
                if(number[i][1].getText() == "")
                {
                    number[i][1].setText(number[i][2].getText());
                    number[i][2].setText("");
                    number[i][0].setText(number[i][1].getText());
                    number[i][1].setText("");

                }
                else {
                    number[i][0].setText(number[i][1].getText());
                    number[i][1].setText(number[i][2].getText());
                    number[i][2].setText("");
                }

            }
            if(number[i][1].getText() == "" && number[i][2].getText()!="")
            {
                space++;
                number[i][1].setText(number[i][2].getText());
                number[i][2].setText("");
            }
            if(number[i][0].getText() != "" && number[i][1].getText()!="")
            {
                if(Integer.parseInt((String)number[i][0].getText()) == Integer.parseInt((String)number[i][1].getText()))
                {
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[i][0].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[i][0].setText(Integer.toString(Integer.parseInt((String) number[i][0].getText())*2));
                    number[i][1].setText(number[i][2].getText());
                    number[i][2].setText("");
                }
            }
            if(number[i][1].getText() != "" && number[i][2].getText()!="")
            {
                //score.setText(number[i][2].getText());
               // highscore.setText(number[i][1].getText());
                if(Integer.parseInt((String) number[i][2].getText()) == Integer.parseInt((String) number[i][1].getText()))
                {
                    //score.setText("Hi");
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[i][1].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[i][1].setText(Integer.toString(Integer.parseInt((String) number[i][1].getText())*2));
                    number[i][2].setText("");
                }
            }
        }
        if(number[0][2].getText() != "" && number[1][2].getText() != "" && number[2][2].getText() != "" && number[0][0].getText() != "" && number[1][0].getText() != "" && number[2][0].getText() != "" && number[0][1].getText() != "" && number[0][2].getText() != "" && number[0][0].getText() != "" && number[2][0].getText() != "" && number[2][1].getText() != "" && number[2][2].getText() != "")
        {
            Intent game = new Intent(MainActivity.this,Gameover.class);
            startActivity(game);
            finish();
        }

        else if(space != 0) {
            Random rand = new Random();
            int v;

            while (true) {
                v = rand.nextInt(3);
                int e = rand.nextInt(3);
                if (number[e][v].getText() == "") {
                    number[e][v].setText(String.valueOf(action));
                    break;
                }
            }
            }
        }

    @SuppressLint("SetTextI18n")
    private void Right() {
        int space = 0;
        for(int i=0;i<3;i++) {
            if (number[i][2].getText() == "" && (number[i][1].getText() != "" || number[i][0].getText() != ""))
            {
                space++;
                if(number[i][1].getText() == "")
                {
                    number[i][2].setText(number[i][0].getText());
                    number[i][0].setText("");
                }
                else {
                    number[i][2].setText(number[i][1].getText());
                    number[i][1].setText(number[i][0].getText());
                    number[i][0].setText("");
                }

            }
            if(number[i][1].getText() == "" &&number[i][0].getText() != "")
            {
                space++;
                number[i][1].setText(number[i][0].getText());
                number[i][0].setText("");
            }
            if(number[i][2].getText() != "" && number[i][1].getText()!="")
            {
                if(Integer.parseInt((String)number[i][2].getText()) == Integer.parseInt((String)number[i][1].getText()))
                {
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[i][2].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[i][2].setText(Integer.toString(Integer.parseInt((String) number[i][2].getText())*2));
                    number[i][1].setText(number[i][0].getText());
                    number[i][0].setText("");
                }
            }
            if(number[i][1].getText() != "" && number[i][0].getText()!="")
            {
                if(Integer.parseInt((String)number[i][0].getText()) == Integer.parseInt((String)number[i][1].getText()))
                {
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[i][1].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[i][1].setText(Integer.toString(Integer.parseInt((String) number[i][1].getText())*2));
                    number[i][0].setText("");
                }
            }
        }
        if(number[0][2].getText() != "" && number[1][2].getText() != "" && number[2][2].getText() != "" && number[0][0].getText() != "" && number[1][0].getText() != "" && number[2][0].getText() != "" && number[0][1].getText() != "" && number[0][2].getText() != "" && number[0][0].getText() != "" && number[2][0].getText() != "" && number[2][1].getText() != "" && number[2][2].getText() != "")        {
            Intent game = new Intent(MainActivity.this,Gameover.class);
            startActivity(game);
            finish();
        }

        else if(space != 0) {
            Random rand = new Random();
            int v;

            while (true) {
                v = rand.nextInt(3);
                int e = rand.nextInt(3);
                if (number[e][v].getText() == "") {
                    number[e][v].setText(String.valueOf(a[rand.nextInt(5)]));
                    break;
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private void Down() {
        int space=0;
        for(int i=0;i<3;i++) {
            if (number[2][i].getText() == "" && (number[1][i].getText() != "" || number[0][i].getText() != ""))
            {
                space++;
                if(number[1][i].getText() == "")
                {
                    number[2][i].setText(number[0][i].getText());
                    number[0][i].setText("");
                }
                else {
                    number[2][i].setText(number[1][i].getText());
                    number[1][i].setText(number[0][i].getText());
                    number[0][i].setText("");
                }

            }
            if(number[1][i].getText() == "" && number[0][i].getText() != "")
            {
                space++;
                number[1][i].setText(number[0][i].getText());
                number[0][i].setText("");
            }
            if(number[2][i].getText() != "" && number[1][i].getText()!="")
            {
                if(Integer.parseInt((String)number[2][i].getText()) ==Integer.parseInt((String) number[1][i].getText()))
                {
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[2][i].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[2][i].setText(Integer.toString(Integer.parseInt((String) number[2][i].getText())*2));
                    number[1][i].setText(number[0][i].getText());
                    number[0][i].setText("");
                }
            }
            if(number[1][i].getText() != ""&&number[0][i].getText()!="")
            {
                if(Integer.parseInt((String)number[0][i].getText()) == Integer.parseInt((String)number[1][i].getText()))
                {
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[1][i].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[1][i].setText(Integer.toString(Integer.parseInt((String) number[1][i].getText())*2));
                    number[0][i].setText("");
                }
            }
        }
        if(number[0][2].getText() != "" && number[1][2].getText() != "" && number[2][2].getText() != "" && number[0][0].getText() != "" && number[1][0].getText() != "" && number[2][0].getText() != "" && number[0][1].getText() != "" && number[0][2].getText() != "" && number[0][0].getText() != "" && number[2][0].getText() != "" && number[2][1].getText() != "" && number[2][2].getText() != "")

        {
            Intent game = new Intent(MainActivity.this,Gameover.class);
            startActivity(game);
            finish();
        }

        else if(space != 0) {
            Random rand = new Random();
            int v;

            while (true) {
                v = rand.nextInt(3);
                int e = rand.nextInt(3);
                if (number[e][v].getText() == "") {
                    number[e][v].setText(String.valueOf(a[rand.nextInt(5)]));
                    break;
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private void UP() {
        int space = 0;
        for(int i=0;i<3;i++) {
            if (number[0][i].getText() == "" && (number[1][i].getText() != ""|| number[2][i].getText() != ""))
            {
                space++;
                if(number[1][i].getText() == "")
                {
                    number[0][i].setText(number[2][i].getText());
                    number[2][i].setText("");
                }
                else {
                    number[0][i].setText(number[1][i].getText());
                    number[1][i].setText(number[2][i].getText());
                    number[2][i].setText("");
                }

            }
            if(number[1][i].getText() == "" && number[2][i].getText() != "")
            {
                space++;
                number[1][i].setText(number[2][i].getText());
                number[2][i].setText("");
            }
            if(number[0][i].getText() != "" && number[1][i].getText()!="")
            {
                if(Integer.parseInt((String)number[0][i].getText()) == Integer.parseInt((String)number[1][i].getText()))
                {
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[0][i].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[0][i].setText(Integer.toString(Integer.parseInt((String) number[0][i].getText())*2));
                    number[1][i].setText(number[2][i].getText());
                    number[2][i].setText("");
                }
            }
            if(number[1][i].getText() != "" && number[2][i].getText()!="")
            {
                if(Integer.parseInt((String)number[2][i].getText()) == Integer.parseInt((String)number[1][i].getText()))
                {
                    space++;
                    score.setText(Integer.toString(Integer.parseInt((String) number[1][i].getText())*2 + Integer.parseInt((String) score.getText())));
                    if(Integer.parseInt((String) highscore.getText())<Integer.parseInt((String) score.getText()))
                    {
                        highscore.setText(score.getText());
                        editor.putInt("High_Score"+action,Integer.parseInt((String) highscore.getText()));
                        editor.commit();
                    }
                    number[1][i].setText(Integer.toString(Integer.parseInt((String) number[1][i].getText())*2));
                    number[2][i].setText("");
                }
            }
        }

        if(number[0][2].getText() != "" && number[1][2].getText() != "" && number[2][2].getText() != "" && number[0][0].getText() != "" && number[1][0].getText() != "" && number[2][0].getText() != "" && number[0][1].getText() != "" && number[0][2].getText() != "" && number[0][0].getText() != "" && number[2][0].getText() != "" && number[2][1].getText() != "" && number[2][2].getText() != "")        {
            //System.out.println("in the if statement");
            Intent game = new Intent(MainActivity.this,Gameover.class);
            startActivity(game);
            finish();
        }
        else if(space != 0) {
            Random rand = new Random();
            int v;
            while (true) {
                v = rand.nextInt(3);
                int e = rand.nextInt(3);
                if (number[e][v].getText() == "") {
                    number[e][v].setText(String.valueOf(a[rand.nextInt(5)]));
                    break;
                }
            }
        }
    }
    public static String  Score()
    {
        return (String) score.getText();
    }
    public static String HighScore()
    {
        return (String) highscore.getText();
    }
}
