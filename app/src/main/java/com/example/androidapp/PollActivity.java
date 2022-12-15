package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class PollActivity extends AppCompatActivity {
    EditText namePoll;
    Poll poll;
    Button btn;
    DBHelper DB;
    DBHelper2 DB2;
    EditText time;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        namePoll = (EditText) findViewById(R.id.inputPollName);
        btn = (Button) findViewById(R.id.buttonAddQuestion2);
        time = (EditText) findViewById(R.id.inputPollTime);
        poll = new Poll();
        poll.id = java.util.UUID.randomUUID();
        poll.questions = null;
        poll.name = "";
        DB = new DBHelper(this);
        DB2 = new DBHelper2(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pollname = namePoll.getText().toString();
                String  timeString = time.getText().toString();
                String pollId = poll.id.toString();
                Integer timeInteger = Integer.parseInt(timeString);
                if(pollname.equals("") || timeInteger == null) {
                    Toast.makeText(PollActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    DB2.insertDataForPolls(pollId, pollname, timeInteger);
                }
                Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("pollId", pollId);
                startActivity(intent);
            }
        });
    }
}