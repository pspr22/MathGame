package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class addition extends AppCompatActivity {

    TextView score,life,time,ques,wel;
    EditText ans;
    Button ok,next;
    Random random = new Random();
    int num1;
    int num2;
    int userAnswer;
    int realAnswer;
    int userScore=0;
    int userLife=3;
    CountDownTimer timer;
    private static final long START_TIME_IN_MILLIS = 60000;
    Boolean timer_running;
    long time_left_in_millis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        getSupportActionBar().hide();

        score = findViewById(R.id.ascore);
        life = findViewById(R.id.alife);
        time = findViewById(R.id.atime);
        ques = findViewById(R.id.queadd);
        wel = findViewById(R.id.txtname);
        ans = findViewById(R.id.etansadd);
        ok = findViewById(R.id.oka);
        next = findViewById(R.id.nexta);

        Intent a = getIntent();
        String MYName = a.getStringExtra("name");
        wel.setText("Welcome "+MYName);

        gameContinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String answ = "0"+ans.getText().toString();

                userAnswer = Integer.valueOf(answ);




                pauseTimer();

                if(userAnswer == realAnswer){
                    userScore = userScore+10;
                    score.setText(""+userScore);
                    ques.setText("Your Answer is correct!!!");

                }
                else{
                    ques.setText("Sorry,You Answer is Wrong!!!");

                    userLife = userLife-1;
                    life.setText(""+userLife);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resetTimer();
                gameContinue();

                if(userLife  <= 0 ){
                    Intent intenta = new Intent(addition.this,finishGame.class);
                    intenta.putExtra("score",userScore);
                    startActivity(intenta);
                }
            }
        });

    }

    public void gameContinue(){

        num1 = random.nextInt(100);
        num2 = random.nextInt(100);

        realAnswer = num1 + num2;
        ques.setText(num1+" + "+num2);
        startTimer();
        ans.setText("");


    }

    public void startTimer(){
        timer = new CountDownTimer(time_left_in_millis,1000) {
            @Override
            public void onTick(long l) {
                time_left_in_millis = l;
                updateText();

            }

            @Override
            public void onFinish() {


                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife-1;
                ques.setText("Sorry!!! Time is up!");
                life.setText(""+userLife);

            }
        }.start();

        timer_running = true;

    }

    private void resetTimer() {

        time_left_in_millis = START_TIME_IN_MILLIS;
        updateText();
    }

    private void pauseTimer() {

        timer.cancel();
        timer_running = false;

    }

    public void updateText(){

        int second = (int) (time_left_in_millis/1000)%60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);


    }



}