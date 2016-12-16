package com.example.mikegau.fyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class LostP extends Activity {

    //Declare variable
    Button reqbtn;
    Button bckbtn;

    EditText Emaillp;

    String emaillptxt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostpass);

        //Locate EditText in lostpass.xml
        Emaillp = (EditText) findViewById(R.id.Emaillp);

        //Locate Button in Lostpass.xml
        reqbtn = (Button) findViewById(R.id.reqbtn);
        bckbtn = (Button) findViewById(R.id.bckbtn);

        //reqbtn click listener
        reqbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //Retrieve the text entered from the EditText
                emaillptxt = Emaillp.getText().toString();

                //Force the user to fill the EditText
                if (emaillptxt.equals("")){

                    Toast.makeText(getApplicationContext(),
                            "Please enter your Email Address",
                            Toast.LENGTH_LONG).show();
                } else {

                    ParseUser.requestPasswordResetInBackground(emaillptxt, new RequestPasswordResetCallback() {

                        public void done(ParseException e) {
                            if (e == null) {

                                Toast.makeText(getApplicationContext(),
                                        "Your password has been sent to your Email",
                                        Toast.LENGTH_LONG).show();

                                startActivity(new Intent(LostP.this,
                                        Home.class));
                            } else {

                                Toast.makeText(getApplicationContext(),
                                        "This Email is not registered",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        bckbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LostP.this,
                        Home.class));
            }
        });
    }
}
