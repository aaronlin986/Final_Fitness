package com.example.final_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText nameText;
    EditText followersText;
    EditText followingText;
    EditText heightText;
    EditText weightText;
    ImageView wo;
    ImageView calend;
    ImageView stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById(R.id.nameProfile);
        followersText = (EditText) findViewById(R.id.followersProfile);
        followingText = (EditText) findViewById(R.id.followingProfile);
        heightText = (EditText) findViewById(R.id.heightProfile);
        weightText = (EditText) findViewById(R.id.weightProfile);
        wo = (ImageView) findViewById(R.id.workouts);
        calend = (ImageView) findViewById(R.id.calendar);
        stat = (ImageView) findViewById(R.id.stats);

        nameText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), countDownTimer.class);
                startActivity(intent);
            }
        });

        calend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), countDownTimer.class);
                startActivity(intent);
            }
        });

        wo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), myList.class);
                startActivity(intent);
            }
        });

        stat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), myList.class);
                startActivity(intent);
            }
        });

    }

}
