package com.example.final_fitness;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class myList extends AppCompatActivity {
    Button add;
    LinearLayout linearLayout;
    ListView list;
    List<String> woList;
    ArrayAdapter<String> woAdapter;
    static int workouts = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist);

        linearLayout = findViewById(R.id.parentLayout);
        list = findViewById(R.id.workoutList);
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
    }

    //Creates new workout entry
    private void createBox(){
        workouts++;
        woList.add("Workout " + workouts);
        woAdapter.notifyDataSetChanged();

        //list.addFooterView(newBox);
        /*LinearLayout workoutLayout = new LinearLayout(getApplicationContext());
        linearLayout.addView(workoutLayout);
        workouts++;
        TextView newBox = new TextView(getApplicationContext());
        newBox.setText("Workout " + workouts);
        newBox.setTextSize(30);
        workoutLayout.addView(newBox);*/
    }

    //Creates a divider
    private void createDivider(){
        View line = new View(getApplicationContext());
        line.setMinimumWidth(100);

        linearLayout.addView(line);
    }


}
