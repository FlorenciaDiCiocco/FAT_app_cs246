package com.team3.fat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.internal.Constants;
import com.team3.fat.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_weight_final extends AppCompatActivity {

    private String dayweight;
    private EditText weightSomething;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    public void saveWeightDay() {
    //I don't know what to do here

    }
}