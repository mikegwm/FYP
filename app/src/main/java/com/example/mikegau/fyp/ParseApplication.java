package com.example.mikegau.fyp;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        //Add your initialization code here
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "w8DSVKNxnsDXmreS1azXkAJNmvmhSrRsCMgtduSa", "ABIcNuI4Vq4Y4afx3nUfr0EIzQk6Dcz4I2egP0we");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        //If you would like all objects to be private by default, remove thi line
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }

}
