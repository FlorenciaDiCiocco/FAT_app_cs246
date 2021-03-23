package com.team3.fat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_weight_final extends AppCompatActivity {

    private String dayweight;
    private EditText weightSomething;
//    private TextView dateTimeDisplay;
//    private Calendar calendar;
//    private SimpleDateFormat dateFormat;
//    private String date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_weight_final);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        EditText editText = (EditText) findViewById(R.id.weightInput);
        addWeightKeyboard keyboard = (addWeightKeyboard) findViewById(R.id.keyboard);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

        weightSomething = (EditText) findViewById(R.id.weightInput);

    }

    public void saveWeightDay(){
        String weight = dayweight;

        Intent intent = getIntent();
        dayweight = intent.getStringExtra("DAYWEIGHT");
        TextView weightObject = findViewById(R.id.weightInput);
        weightObject.setText(weight);

        SharedPreferences sharedPref = getSharedPreferences("dailyWeight.txt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("DAYWEIGHT", dayweight);
        editor.apply();

        String gettingConfusedWithTheseVariables = sharedPref.getString("DAYWEIGHT", "");
        weightSomething.setText(dayweight);

    }
}