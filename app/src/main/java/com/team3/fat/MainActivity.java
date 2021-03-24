package com.team3.fat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth Auth;
    EditText userEmail, userPassword;
    Button newUser, forgotPass, login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = findViewById(R.id.regEmail);
        userPassword = findViewById(R.id.confirmPass);
        Auth = FirebaseAuth.getInstance();

        newUser = findViewById(R.id.newUser);
        forgotPass = findViewById(R.id.forgotPass);
        login = findViewById(R.id.Login);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_registerUser();
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_forgotPassword();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringEmail = userEmail.getText().toString().trim();
                String stringPassword = userPassword.getText().toString().trim();
                Globals.setuserEmail(stringEmail);
                Globals.setuserpassword(stringPassword);

                if (TextUtils.isEmpty(stringEmail)){
                    Toast.makeText(MainActivity.this, "Please enter a Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(stringPassword)){
                    Toast.makeText(MainActivity.this,"Please enter a Password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Auth.signInWithEmailAndPassword(stringEmail, stringPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            startActivity(new Intent(getApplicationContext(), MainActivity2_bottom_bar.class));  //change this to the main activity after login and also change all loginMain to MainActivity
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Login Failed, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void open_registerUser(){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }

    public void open_forgotPassword(){
        Intent intent = new Intent(this, MainActivity.class); //ForgotPass.class
        startActivity(intent);
    }

    //Weight type convert button
    //public void onClick(View view){
    //    Button button = (Button) findViewById(R.id.weight_type_button);
    //    button.setText("Yo yo yo");

    //}

}
