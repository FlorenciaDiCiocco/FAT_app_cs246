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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.team3.fat.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class add_weight_final extends AppCompatActivity {

    private String dayweight;
    private EditText weightSomething;
    Button adding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //we show the activity
        setContentView(R.layout.activity_add_weight_final);
    }


    public void Save_input(View view) {
        EditText input_txt = findViewById(R.id.input_text);
        float number = Float.valueOf(input_txt.getText().toString());
        ListItem input = new ListItem();

        //this block is for the date
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        input.setDate(formattedDate);
        input.setWeight(number);
        Globals.set_weight(input);
        startActivity(new Intent(getApplicationContext(), MainActivity2_bottom_bar.class));
    }



    public void saveWeightDay() {
    //I don't know what to do here
        Toast weightToast = Toast.makeText(this, "Weight Not Saved!!", Toast.LENGTH_LONG);
        weightToast.show();
    }
}