package com.example.mikegau.fyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Home extends Activity {


    Button Login_button;
    Button LostP_button;
    Button Register_button;
    String usernametxt;
    String passwordtxt;
    EditText Username;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Locate EditTexts in activity_home.xml
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);

        //Locate Buttons in activity_home.xml
        Login_button = (Button) findViewById(R.id.Login_button);
        LostP_button = (Button) findViewById(R.id.LostP_button);
        Register_button = (Button) findViewById(R.id.Register_button);

        //Login Button Click Listener
        Login_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //Retrieve the text entered from the EditText
                usernametxt = Username.getText().toString();
                passwordtxt = Password.getText().toString();

                //Send data to Parse.com for verification
                ParseUser.logInInBackground(usernametxt, passwordtxt,
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user != null){

                                    //If user exist and authenticated, send user to
                                    Intent intent = new Intent(
                                            Home.this,
                                            Welcome.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),
                                    "Successfully logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                } else{
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "No such user exist, please signup",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
            }
        });

      Register_button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                        public void onClick(View v) {
                                                startActivity(new Intent(Home.this,
                                                        Register.class));
                                            }
                                         }

      );

        LostP_button.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                        public void onClick(View v) {
                                                startActivity(new Intent(Home.this,
                                                        LostP.class));
                                            }
        });

        }

    }


