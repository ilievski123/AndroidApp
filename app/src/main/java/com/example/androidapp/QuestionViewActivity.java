package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionViewActivity extends AppCompatActivity {
    ArrayList<Questions> questions;
    Questions q;
    DBHelperPolls DBPolls;
    String pollId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);
        questions = new ArrayList<Questions>();
        DBPolls = new DBHelperPolls(this);
        q = new Questions("", "", "", "", "", "", "");


        Intent i = getIntent();
        pollId = (String) i.getSerializableExtra("pollId");

        Log.d("ID: ", pollId);

        storeDataInArraysQuestions();

        Log.d("Prasanje 0", questions.get(0).getName());
        Log.d("Prasanje 1", questions.get(1).getName());
    }

    void storeDataInArraysQuestions() {
        Cursor cursor = DBPolls.readAllDataFromQuestionsByID(pollId);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                q = new Questions("", "", "", "", "", "", "");
                q.setId(cursor.getString(0));
                q.setName(cursor.getString(1));
                q.setAnswer1(cursor.getString(2));
                q.setAnswer2(cursor.getString(3));
                q.setAnswer3(cursor.getString(4));
                q.setAnswer4(cursor.getString(5));
                q.setPollId(cursor.getString(6));
                questions.add(q);
            }
        }
    }
}

