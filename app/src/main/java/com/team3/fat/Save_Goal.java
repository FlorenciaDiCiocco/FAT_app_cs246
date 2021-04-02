package com.team3.fat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Save_Goal extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__goal);
    }

    public void Save_input_goal (View view){
        EditText input_txt = findViewById(R.id.input_goal);
        float number = Float.valueOf(input_txt.getText().toString());
        Globals.set_Goal(number);


        db.collection("Users").document(Globals.getuserEmail())
                .set(Globals._user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Email, password, and goal written");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing email, password, and goal", e);
                    }
                });

        startActivity(new Intent(getApplicationContext(), MainActivity2_bottom_bar.class));
    }
}