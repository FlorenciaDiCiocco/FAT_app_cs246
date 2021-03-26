/*
package com.team3.fat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class addWeight extends AppCompatActivity {

    //Note for Lucy: This file = MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);
        //setContentView(R.layout.activity_add_weight_keyboard);

        EditText editText = (EditText) findViewById(R.id.weightInput);
        addWeightKeyboard keyboard = (addWeightKeyboard) findViewById(R.id.keyboard);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }
}*/
