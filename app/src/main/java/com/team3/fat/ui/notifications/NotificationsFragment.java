package com.team3.fat.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.team3.fat.R;

public class NotificationsFragment extends Fragment  implements View.OnClickListener{

    private NotificationsViewModel notificationsViewModel;
    public int peachy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.feetHeight);

        String [] values =
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
        return root;
    }

    public void calculateBMI(View v){
        //this will calculate the BMI eventually
        //the formula to do so is kg/m^2
        //otherwise calculate as (lbs/m^2)*703
        int ftToInch;
        int totalInch;
        int lizard;

        //process of turning feetHeight input to an int so it can be used for calculating BMI
        EditText et = (EditText) v.findViewById(R.id.feetHeight);
        String hello = et.getText().toString();
        //Sorry for the variable names, but I am getting myself confused in trying to name them all relative to what they are
        int horse = Integer.parseInt(hello);

        //feetHeight * 12 = cm (convert feet to inches)
        //ftToInch is the total inches of the feet input
        ftToInch = horse * 12;

        EditText butterfly = (EditText) v.findViewById(R.id.inchesHeight);
        String goodbye = butterfly.getText().toString();
        //Sorry for the variable name, but I am getting myself confused in trying to name them all relative to what they are
        int donkey = Integer.parseInt(goodbye);

        EditText rainbow = (EditText) v.findViewById(R.id.calcWithWeight);
        String sorta = rainbow.getText().toString();
        //Sorry for the variable name, but I am getting myself confused in trying to name them all relative to what they are
        int mule = Integer.parseInt(sorta);

        //(feetHeight * 12) + inchesHeight (add feet in inches to additional inches) to get total height in inches
        // totalInch = total height in inches
        totalInch = ftToInch + donkey;

        //(total Height in inches)^2
        lizard = totalInch * totalInch;

        //divide the weight given by lizard
        peachy = (mule/lizard);
    }
    @Override
    public void onClick(View v) {
        calculateBMI(v);
        TextView tv = (TextView) v.findViewById(R.id.numBMI);
        tv.setText(peachy);

        }
}