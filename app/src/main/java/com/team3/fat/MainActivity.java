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

        Auth = FirebaseAuth.getInstance();

        //connect variables with the XML id
        userEmail = findViewById(R.id.regEmail);
        userPassword = findViewById(R.id.confirmPass);
        newUser = findViewById(R.id.newUser);
        forgotPass = findViewById(R.id.forgotPass);
        login = findViewById(R.id.Login);

        //when the new user button is pushed then open the RegisterUser activity
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_registerUser();
            }
        });

        //when the Forgot password button is pushed then open the ForgotPass activity
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_forgotPassword();
            }
        });

        //when the login button is pressed authenticate the user and if successful move to the app Home Page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert the user inputs to Strings and trim them.
                String stringEmail = userEmail.getText().toString().trim();
                String stringPassword = userPassword.getText().toString().trim();

                //check to see if the user entered a email address.
                if (TextUtils.isEmpty(stringEmail)){
                    Toast.makeText(MainActivity.this, "Please enter a Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //check to see if the user entered a password.
                if (TextUtils.isEmpty(stringPassword)){
                    Toast.makeText(MainActivity.this,"Please enter a Password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //send the Email and password to Firebase to be authenticated.
                Auth.signInWithEmailAndPassword(stringEmail, stringPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Login is successful. Take the user to the Home page.
                        if (task.isSuccessful()){
                            //I moved the setters to here because they were called before making sure that the user had input anything into them.
                            //If they are placed here then we make sure that we send something useful.
                            Globals.setuserEmail(stringEmail);
                            Globals.setuserpassword(stringPassword);
                            startActivity(new Intent(getApplicationContext(), MainActivity2_bottom_bar.class));
                        }
                        //Something went wrong with the login. Email or password could be wrong.
                        else{
                            Toast.makeText(MainActivity.this, "Login Failed, Please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //function to open the RegisterUser activity.
    public void open_registerUser(){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }

    //function to open the ForgotPass activity.
    public void open_forgotPassword(){
        Intent intent = new Intent(this, ForgotPass.class);
        startActivity(intent);
    }

    //Weight type convert button
    //public void onClick(View view){
    //    Button button = (Button) findViewById(R.id.weight_type_button);
    //    button.setText("Yo yo yo");

    //}

}
