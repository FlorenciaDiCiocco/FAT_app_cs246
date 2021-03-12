package com.team3.fat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterUser extends AppCompatActivity {

    private FirebaseAuth Auth;
    EditText regPass, regEmail, confirmPass;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        regEmail = findViewById(R.id.regEmail);
        regPass = findViewById(R.id.regPass);
        confirmPass = findViewById(R.id.confirmPass);
        signUp = findViewById(R.id.signUp);
        Auth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regEmail.getText().toString().trim();
                String password = regPass.getText().toString().trim();
                String confirmPassword = confirmPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterUser.this, "Please enter a Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterUser.this, "Please enter a Password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 8){
                    Toast.makeText(RegisterUser.this, "Please enter a Password that is at least 8 characters.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(RegisterUser.this, "Please confirm Password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals(confirmPassword)){
                    Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(RegisterUser.this, "Existing user registered with this Email.", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(RegisterUser.this, "Sign up Successful.", Toast.LENGTH_SHORT).show();
                                open_Login();

                            }
                        }
                    });

                }
                else Toast.makeText(RegisterUser.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void open_Login(){
        Intent intent = new Intent(this, MainActivity.class);//registerUser.class
        startActivity(intent);
    }
}