package com.example.final_fitness;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class countDownTimer extends AppCompatActivity  {
    TextView min;
    TextView sec;
    Button start;
    Time totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer);

        min = (TextView) findViewById(R.id.minutes);
        sec = (TextView) findViewById(R.id.seconds);
        start = (Button) findViewById(R.id.startTime);
        //testtest
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                totalTime = new Time(Long.parseLong(min.getText().toString()) * 60 * 1000 + Long.parseLong(sec.getText().toString()) * 1000);
                start.setVisibility(View.INVISIBLE);
                new CountDownTimer(totalTime.getTime(), 1000){
                    public void onTick(long millisUntilFinished) {
                        totalTime.setTime(millisUntilFinished);
                        min.setText(Long.toString(millisUntilFinished / (60 * 1000)));
                        sec.setText(String.format("%02d", (millisUntilFinished / 1000) % 60));
                    }

                    public void onFinish() {
                        start.setVisibility(View.VISIBLE);
                        min.setText("1");
                        sec.setText("00");
                    }
                }.start();
            }
        });
    }
}
