package com.example.final_fitness;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class myList extends AppCompatActivity {
    Time totalTime;
    long total;
    ProgressBar timer;
    Button startRoutine;
    Button clearer;
    EditText min;
    EditText sec;
    EditText item;
    Button add;
    LinearLayout linearLayout;
    ListView list;
    List<String> woList;
    List<Time> times;
    ArrayAdapter<String> woAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist);

        min = (EditText) findViewById(R.id.durationM);
        sec = (EditText) findViewById(R.id.durationS);
        item = (EditText) findViewById(R.id.titleBar);
        linearLayout = findViewById(R.id.parentLayout);
        list = findViewById(R.id.workoutList);
        clearer = (Button) findViewById(R.id.clear);
        startRoutine = (Button) findViewById(R.id.begin);
        timer = (ProgressBar) findViewById(R.id.progressTime);

        times = new ArrayList<Time>();
        woList = new ArrayList<String>();
        woAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, woList);
        list.setAdapter(woAdapter);
        add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBox();
            }
        });

        clearer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                woList.clear();
                times.clear();
                woAdapter.notifyDataSetChanged();
            }
        });

        startRoutine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(!times.isEmpty()){
                    totalTime = times.get(0);
                    total = totalTime.getTime();
                    min.setEnabled(false);
                    sec.setEnabled(false);
                    item.setEnabled(false);
                    timer.setProgress(100);
                    startRoutine.setVisibility(View.INVISIBLE);
                    new CountDownTimer(times.get(0).getTime(), 1000){
                        public void onTick(long millisUntilFinished) {
                            totalTime.setTime(millisUntilFinished);
                            timer.setProgress((int) ((millisUntilFinished * 100) / total));
                        }
                        public void onFinish() {
                            min.setEnabled(true);
                            sec.setEnabled(true);
                            item.setEnabled(true);
                            times.remove(0);
                            startRoutine.setVisibility(View.VISIBLE);
                            woList.remove(0);
                            woAdapter.notifyDataSetChanged();
                        }
                    }.start();
                }
            }
        });
    }

    private void createBox(){
        times.add(new Time(Long.parseLong(min.getText().toString()) * 60 * 1000 + Long.parseLong(sec.getText().toString()) * 1000));
        String entry = min.getText().toString() + ":" + sec.getText().toString();
        woList.add(item.getText().toString() + " " + entry);
        woAdapter.notifyDataSetChanged();
    }
}
