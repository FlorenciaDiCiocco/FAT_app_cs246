package com.team3.fat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth Auth;
    EditText userEmail, userPassword;
    Button newUser, forgotPass, login;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";


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
                            // need to set the values to default incase user logs out then create a new account.
                            Globals.set_weight_type(Weight_type.pounds);
                            Globals.set_Goal(0);
                            Globals._user.list_of_input = new ArrayList<> ();
                            /*
                            * Store in Firestore
                            */

                            DocumentReference docRef = db.collection("Users").document(Globals.getuserEmail());
                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            Globals._user = document.toObject(Users.class);

                                        } else {
                                            db.collection("Users").document(Globals.getuserEmail())
                                                    .set(Globals._user)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "New DocumentSnapshot successfully written!");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error writing New document", e);
                                                        }
                                                    });
                                        }
                                    } else {
                                        Log.d(TAG, "get failed with ", task.getException());
                                    }
                                }
                            });
/*
                            DocumentReference docRef = db.collection("Users").document(Globals.getuserEmail());
                            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    Users user = documentSnapshot.toObject(Users.class);
                                    Globals.setuserEmail(user.userEmail);
                                    Globals.setuserpassword(user.password);
                                    Globals.set_Goal(user.weight_goal);
                                    Globals._user.list_of_input = user.list_of_input;
                                    Globals.set_weight_type(user.weight_type);



                                }
                            });

*/

                            /*
                            DocumentReference docRef = db.collection("Users").document(stringEmail);
                            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    Users user = documentSnapshot.toObject(Users.class);
                                    Globals._user = user;

                                }
                            });

                                db.collection("Users").document(Globals.getuserEmail())
                                        .set(Globals._user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "New DocumentSnapshot successfully written!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error writing New document", e);
                                            }
                                        });

                             */







                            /*
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("email" , stringEmail);
                            userInfo.put("password" , stringPassword);
                            userInfo.put("goal", Globals.get_Goal());
                            userInfo.put("weightlist", Globals.get_weight_list());
                            userInfo.put("weight Type", Globals.get_weight_type());
                            db.collection("Users").document(stringEmail)
                                    .set(userInfo)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error writing document", e);
                                        }
                                    });


                             */
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
