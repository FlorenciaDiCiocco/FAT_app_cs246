package com.team3.fat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Save_Goal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__goal);
    }

    public void Save_input_goal (View view){
        EditText input_txt = findViewById(R.id.input_goal);
        float number = Float.valueOf(input_txt.getText().toString());
        Globals.set_Goal(number);
        startActivity(new Intent(getApplicationContext(), MainActivity2_bottom_bar.class));
    }
}