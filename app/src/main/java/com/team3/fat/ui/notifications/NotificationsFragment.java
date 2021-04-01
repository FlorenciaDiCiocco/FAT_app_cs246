package com.team3.fat.ui.notifications;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.team3.fat.Globals;
import com.team3.fat.R;
import com.team3.fat.Save_Goal;
import com.team3.fat.Weight_type;

import java.util.Scanner;

public class NotificationsFragment extends Fragment{

    private NotificationsViewModel notificationsViewModel;

    public double peachy;
    String pear;
    TextView _mygoal;
    TextView bmi;
    Button Changing,Lucy,Toggle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.feetHeight);

        //This code section is for the spinner found on the settings page.
        //It does not take an input from the user and do anything with it, it is only there to display information to the user.
        String[] values =
                {"BMI Categories", "< 16.0\tSeverely Underweight", "16.0 - 18.4\tUnderweight", "18.5 - 24.9\tNormal",
                        "25.0 - 29.9\tOverweight", "30.0 - 34.9\tModerately Obese", "35.0 - 39.9\tSeverely Obese",
                        "> 40.0\tMorbidly Obese",};
        Spinner spinner = (Spinner) root.findViewById(R.id.BMIspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //I'll show the weight goal here:
        _mygoal = root.findViewById(R.id.actual_weight_goal);
        if (Globals.get_Goal() == 0) {
            _mygoal.setText("You haven't added a goal yet.\nYou can do it here:");
        } else {
            _mygoal.setText("Your goal is: " + Globals.get_Goal() + ".\nYou can change it here:");
        }


        //Here we call change weight goal
        Changing = root.findViewById(R.id.change_goal);
        Changing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weightIntent = new Intent(getActivity(), Save_Goal.class);
                startActivity(weightIntent);
            }
        });

        //Here we calculate BMI
        Lucy = root.findViewById(R.id.buttonBMI);
        Lucy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI(root);
            }
        });

        //Here we call change weight goal
        Toggle = root.findViewById(R.id.toggleWeightType);
        Toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Globals.toggleWeightType(Globals._user);
            }
        });

        return root;
    }

    public void calculateBMI(View v) {
        //this will calculate the BMI eventually, the formula to do so is kg/m^2, otherwise calculate as (lbs/m^2)*703
        //Sorry for the variable names, but I am getting myself confused in trying to name them all relative to what they are

        //process of turning feetHeight input to an int so it can be used for calculating BMI
        EditText etFeet = (EditText) v.findViewById(R.id.feetHeight);
        String stringFeetHeight = etFeet.getText().toString(); //converts input to a string
        int intFeetHeight = Integer.parseInt(stringFeetHeight); //converts string to an int

        //ftToInch is the total inches of the feet input
        int ftToInch = intFeetHeight * 12; //feetHeight * 12 = cm (convert feet to inches)

        EditText etInchesHeight = (EditText) v.findViewById(R.id.inchesHeight);
        String stringInchesHeight = etInchesHeight.getText().toString(); //converts input to a string
        int intInchesHeight = Integer.parseInt(stringInchesHeight); //converts string to an int

        EditText etCalcWithWeight = (EditText) v.findViewById(R.id.calcWithWeight);
        String stringCalcWithWeight = etCalcWithWeight.getText().toString(); //converts input to a string
        int intCalcWithWeight = Integer.parseInt(stringCalcWithWeight); //converts string to an int

        //(feetHeight * 12) + inchesHeight (add feet-in-inches to additional inches) to get total height in inches
        int totalInch = ftToInch + intInchesHeight; // totalInch = total height in inches
        int inchesSquared = totalInch * totalInch; //(total Height in inches)^2

        double result = (double) intCalcWithWeight / inchesSquared; //divide the weight given by lizard

        //this determines if there is a need to convert the lbs to kg and displays the info
        bmi = v.findViewById(R.id.numBMI);
        if(Globals.get_weight_type()== Weight_type.pounds){
            result=result*703;
        }

        //displays the results
        result= Math.round(result);
            bmi.setText("Your BMI is: " + result +".");
    }
}