package com.example.final_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editInfo extends AppCompatActivity {
    Button submitButton;
    EditText nameField;
    EditText heightField;
    EditText weightField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        nameField = (EditText) findViewById(R.id.editName);
        heightField = (EditText) findViewById(R.id.editHeight);
        weightField = (EditText) findViewById(R.id.editWeight);
        submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                MainActivity.datas[0] = nameField.getText().toString();
                MainActivity.datas[1] = heightField.getText().toString();
                MainActivity.datas[2] = weightField.getText().toString();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
