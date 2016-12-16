package com.example.mikegau.fyp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.ParseUser;

public class EditNoteActivity extends Activity {

    private Note note;
    private EditText titleEditText;
    private EditText contentEditText;
    private String postTitle;
    private String postContent;
    private Button saveNoteButton;
    private Button deleteNoteButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent intent = this.getIntent();

        titleEditText = (EditText) findViewById(R.id.noteTitle);
        contentEditText = (EditText) findViewById(R.id.noteContent);

        if (intent.getExtras() != null) {

            note = new Note(intent.getStringExtra("noteId"), intent.getStringExtra("noteTitle"), intent.getStringExtra("noteContent"));

            titleEditText.setText(note.getTitle());
            contentEditText.setText(note.getContent());
        }


        saveNoteButton = (Button) findViewById(R.id.saveNote);
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveNote();

                Intent intent = new Intent(EditNoteActivity.this,
                        Content.class);
                startActivity(intent);
            }
        });


        deleteNoteButton = (Button) findViewById(R.id.deleteNote);
        deleteNoteButton.setOnClickListener(new OnClickListener(){

                                                @Override
                                                public void onClick(View v){
                                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
                                                    query.getInBackground(note.getId(), new GetCallback<ParseObject>() {
                                                        @Override
                                                        public void done(ParseObject post, ParseException e) {

                                                            post.deleteInBackground();

                                                        }
                                                    });


                                                    finish();
                                                }
                                            }

        );

        cancelButton = (Button) findViewById(R.id.cancelNote);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }



    private void saveNote() {

        postTitle = titleEditText.getText().toString();
        postContent = contentEditText.getText().toString();

        postTitle = postTitle.trim();
        postContent = postContent.trim();

        //If user doesn't enter a title or content, do nothing
        //If user enters title, but no content, save
        //If user enters content with no title, give warning
        //If user enters both title and content, save

        if (!postTitle.isEmpty()) {

            //Check if post is being created or edited

            if (note == null) {

                //Create new post
                final ParseObject post = new ParseObject("Post");
                post.put("title", postTitle);
                post.put("content", postContent);
                post.put("author", ParseUser.getCurrentUser());
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            //Saved successfully
                            note = new Note(post.getObjectId(), postTitle, postContent);
                            Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_LONG).show();
                            Log.d(getClass().getSimpleName(), "User update error: " + e);
                        }
                    }
                });
            } else {

                //update post

                ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

                //Retrieve the object by id
                query.getInBackground(note.getId(), new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject post, ParseException e) {

                        if (e == null) {

                            //Now update with the new data
                            post.put("title", postTitle);
                            post.put("content", postContent);
                            post.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {

                                    if (e == null) {

                                        //Saved successfully
                                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                                    } else {

                                        //The save failed
                                        Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_LONG).show();
                                        Log.d(getClass().getSimpleName(), "User update error: " + e);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } else if (postTitle.isEmpty() && !postContent.isEmpty()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(EditNoteActivity.this);
            builder.setMessage(R.string.edit_error_message)
                    .setTitle(R.string.edit_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }



    }

}



