package com.example.final_fitness;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;

public class countDownTimer extends AppCompatActivity  {
    EditText min;
    EditText sec;
    Button start;
    Button clearer;
    Time totalTime;
    ProgressBar progress;
    long total;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntimer);

        progress = (ProgressBar) findViewById(R.id.timeBar);
        min = (EditText) findViewById(R.id.minutes);
        sec = (EditText) findViewById(R.id.seconds);
        start = (Button) findViewById(R.id.startTime);

        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                totalTime = new Time(Long.parseLong(min.getText().toString()) * 60 * 1000 + Long.parseLong(sec.getText().toString()) * 1000);
                total = totalTime.getTime();
                min.setEnabled(false);
                sec.setEnabled(false);
                progress.setProgress(100);
                start.setVisibility(View.INVISIBLE);
                new CountDownTimer(totalTime.getTime(), 1000){
                    public void onTick(long millisUntilFinished) {
                        totalTime.setTime(millisUntilFinished);
                        min.setText(Long.toString(millisUntilFinished / (60 * 1000)));
                        sec.setText(String.format("%02d", (millisUntilFinished / 1000) % 60));
                        progress.setProgress((int) ((millisUntilFinished * 100) / total));
                    }

                    public void onFinish() {
                        start.setVisibility(View.VISIBLE);
                        min.setEnabled(true);
                        sec.setEnabled(true);
                    }
                }.start();
            }
        });
    }
}
