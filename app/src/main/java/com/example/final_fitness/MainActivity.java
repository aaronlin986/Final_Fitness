package com.example.final_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView nameText;
    TextView followersText;
    TextView followingText;
    TextView heightText;
    TextView weightText;
    Button tempButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (TextView) findViewById(R.id.nameProfile);
        followersText = (TextView) findViewById(R.id.followersProfile);
        followingText = (TextView) findViewById(R.id.followingProfile);
        heightText = (TextView) findViewById(R.id.heightProfile);
        weightText = (TextView) findViewById(R.id.weightProfile);
        tempButton = (Button) findViewById(R.id.nextPage);

        tempButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), countDownTimer.class);
                startActivity(intent);
            }
        });

    }

}
