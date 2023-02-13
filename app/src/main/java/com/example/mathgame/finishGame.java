package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class finishGame extends AppCompatActivity {

    TextView scorel,cong;
    Button playAgain,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        getSupportActionBar().hide();

        scorel = findViewById(R.id.scorel);
        playAgain = findViewById(R.id.playagain);
        exit = findViewById(R.id.exit);
        cong = findViewById(R.id.cong);


        Intent intentm = getIntent();
        int userscorem = intentm.getIntExtra("score",0);
        scorel.setText("Your score:- "+userscorem);
        if(userscorem == 0)
            cong.setText("Better Luck Next Time");

        Intent intenta = getIntent();
        int userscorea = intenta.getIntExtra("score",0);
        scorel.setText("Your score:- "+userscorea);
        if(userscorea == 0)
            cong.setText("Better Luck Next Time");

        Intent intents = getIntent();
        int userscores = intents.getIntExtra("score",0);
        scorel.setText("Your score:- "+userscores);
        if(userscores == 0)
            cong.setText("Better Luck Next Time");

        Intent intentq = getIntent();
        int userscoreq = intentq.getIntExtra("score",0);
        scorel.setText("Your score:- "+userscoreq);
        if(userscoreq == 0)
            cong.setText("Better Luck Next Time");



        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(finishGame.this,MainActivity.class);
                startActivity(i);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });
    }
}