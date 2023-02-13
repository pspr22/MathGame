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

public class square extends AppCompatActivity {

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
        setContentView(R.layout.activity_square);

        getSupportActionBar().hide();

        score = findViewById(R.id.qscore);
        life = findViewById(R.id.qlife);
        time = findViewById(R.id.qtime);
        ques = findViewById(R.id.quesqa);
        wel = findViewById(R.id.txtqname);
        ans = findViewById(R.id.etanssqa);
        ok = findViewById(R.id.okq);
        next = findViewById(R.id.nextq);

        Intent q = getIntent();
        String MYName = q.getStringExtra("name");
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
                    Intent intentq = new Intent(square.this,finishGame.class);
                    intentq.putExtra("score",userScore);
                    startActivity(intentq);
                }
            }
        });

    }

    public void gameContinue(){

        num1 = random.nextInt(100);

        realAnswer = (int) Math.pow(num1,2);
        ques.setText(num1+" ^ 2");
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

    public void resetTimer() {

        time_left_in_millis = START_TIME_IN_MILLIS;
        updateText();
    }

    public void updateText(){

        int second = (int) ((time_left_in_millis/1000)%60);
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);


    }




    private void pauseTimer() {

        timer.cancel();
        timer_running = false;

    }


}