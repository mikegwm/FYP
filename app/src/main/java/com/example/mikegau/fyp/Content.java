package com.example.mikegau.fyp;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;


public class Content extends ListActivity{

    private List<Note> posts;

    Button newthreadbtn;
    Button refreshbtn;
    Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.content);

        //Locate Button in content.xml
        newthreadbtn = (Button) findViewById(R.id.newthreadbtn);
        refreshbtn = (Button) findViewById(R.id.refreshbtn);
        exit = (Button) findViewById(R.id.exit);

        posts = new ArrayList<Note>();
        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, R.layout.list_item_layout, posts);
        setListAdapter(adapter);

        refreshPostList();

        setProgressBarIndeterminateVisibility(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Note note = posts.get(position);
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("noteId", note.getId());
        intent.putExtra("noteTitle", note.getTitle());
        intent.putExtra("noteContent", note.getContent());
        startActivity(intent);

    }

    private void refreshPostList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");



        query.findInBackground(new FindCallback<ParseObject>() {


            @Override
            public void done(List<ParseObject> postList, ParseException e) {

                setProgressBarIndeterminateVisibility(false);

                if (e == null) {

                    //If there are result, update the list of posts
                    //and notify the adapter
                    posts.clear();

                    for (ParseObject post : postList) {

                        Note note = new Note(post.getObjectId(), post.getString("title"), post.getString("content"));
                        posts.add(note);
                    }

                    ((ArrayAdapter<Note>) getListAdapter()).notifyDataSetChanged();
                } else {

                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }


        });


        newthreadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Content.this,
                        EditNoteActivity.class);
                startActivity(intent);


            }
        });

        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshPostList();

            }
        });

       exit.setOnClickListener(new View.OnClickListener(){

           @Override
       public void onClick(View v){


               Intent intent = new Intent(Intent.ACTION_MAIN);
               intent.addCategory(Intent.CATEGORY_HOME);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);

           }
       });

    }


}

