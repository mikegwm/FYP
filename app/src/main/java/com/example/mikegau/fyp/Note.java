package com.example.mikegau.fyp;

import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Note {

    private String id;
    private String title;
    private String content;

    Note(String noteId, String noteTitle, String noteContent){

        id = noteId;
        title = noteTitle;
        content = noteContent;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title){

        this.title = title;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content){

        this.content = content;
    }

    @Override
    public String toString(){

        return this.getTitle();
    }


}

