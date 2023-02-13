package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    EditText etname;
    Spinner spinner;
    ArrayAdapter adapter;
    String options[];
    ConstraintLayout csLayout;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = findViewById(R.id.etname);
        listView = findViewById(R.id.listview);
        csLayout = findViewById(R.id.mainly);

        options = getResources().getStringArray(R.array.option);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("Addition")){
                    Intent a = new Intent(getApplicationContext(),addition.class);
                    String my_name = etname.getText().toString();
                    a.putExtra("name",my_name);
                    startActivity(a);
                }
                else  if(adapterView.getItemAtPosition(i).equals("Subtration")){
                    Intent s = new Intent(MainActivity.this,subtraction.class);
                    String my_name = etname.getText().toString();
                    s.putExtra("name",my_name);
                    startActivity(s);
                }
                else if(adapterView.getItemAtPosition(i).equals("Multiplication")){
                    Intent m = new Intent(MainActivity.this,multiplication.class);
                    String my_name = etname.getText().toString();
                    m.putExtra("name",my_name);
                    startActivity(m);
                }
                else if(adapterView.getItemAtPosition(i).equals("Square")){
                    Intent q = new Intent(MainActivity.this,square.class);
                    String my_name = etname.getText().toString();
                    q.putExtra("name",my_name);
                    startActivity(q);
                }

            }
        });




    }

}