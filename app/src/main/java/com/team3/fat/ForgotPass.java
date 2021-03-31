package com.team3.fat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    private FirebaseAuth Auth;
    EditText userEmail;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        Auth = FirebaseAuth.getInstance();

        userEmail = findViewById(R.id.editUserEmail);
        sendEmail = findViewById(R.id.sendEmail);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = userEmail.getText().toString().trim();

                if (TextUtils.isEmpty(User)){
                    Toast.makeText(ForgotPass.this, "Please enter a Valid Email.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Auth.sendPasswordResetEmail(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPass.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}