package com.example.mikegau.fyp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class Welcome extends Activity {

    Button prcd;
    Button logoutw;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the view from welcome.xml
        setContentView(R.layout.welcome);

        //Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        //Convert currentuser into String
        String struser = currentUser.getUsername().toString();

        //Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        //Set the currentUser String into TextView
        txtuser.setText("You are logged in as " +struser);

        //Locate Button in welcome.xml
        prcd = (Button) findViewById(R.id.prcd);
        logoutw = (Button) findViewById(R.id.logoutw);

        //prcd Button Clicker Listener
        prcd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,
                        Content.class));
            }
        });

        logoutw.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Logout current user
                ParseUser.logOut();
                finish();

                Toast.makeText(
                        getApplicationContext(),
                        "Logged out",
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(Welcome.this,
                        Home.class));
            }
        });
    }
}
