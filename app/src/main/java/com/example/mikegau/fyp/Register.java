package com.example.mikegau.fyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends Activity{


    //Declare Variables
    Button Reg;
    Button cancel_reg;

    EditText Passwordregg;
    EditText Usernameregg;
    EditText Email;

    String usertxt;
    String passtxt;
    String emailtxt;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //Locate EditText in Register.xml
        Usernameregg = (EditText) findViewById(R.id.Usernameregg);
        Passwordregg = (EditText) findViewById(R.id.Passwordregg);
        Email = (EditText) findViewById(R.id.Email);

        //Locate Button in Register.xml
        Reg = (Button) findViewById(R.id.Reg);
        cancel_reg = (Button) findViewById(R.id.cancel_reg);

        //Register Button Click listener
        Reg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //Retrieve the text entered from the EditText
                usertxt = Usernameregg.getText().toString();
                passtxt = Passwordregg.getText().toString();
                emailtxt = Email.getText().toString();

                //Force user to complete the information
                if (usertxt.equals("") && passtxt.equals("") && emailtxt.equals("")) {

                    Toast.makeText(getApplicationContext(),
                            "Please fill all of your data",
                            Toast.LENGTH_LONG).show();
                } else {

                    //Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(usertxt);
                    user.setPassword(passtxt);
                    user.setEmail(emailtxt);

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {

                                //Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully registered",
                                        Toast.LENGTH_LONG).show();

                                startActivity(new Intent(Register.this,
                                        Home.class));
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "User or Email has been used, or the email you entered is invalid",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

        //Cancel Button click listener
        cancel_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,
                        Home.class));
            }
        });
    }




}






