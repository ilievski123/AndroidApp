package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowAnswersUserActivity extends AppCompatActivity {
    DBHelperUserAnswers DBAnswers;
    ShowAnswerAdapter showAnswerAdapter;
    RecyclerView recyclerView;
    String username;
    ArrayList<String> questions, answers, dates;
    String pollId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answers_user);

        recyclerView = new RecyclerView(this);
        DBAnswers = new DBHelperUserAnswers(this);
        questions = new ArrayList<String>();
        answers = new ArrayList<String>();
        dates = new ArrayList<String>();


        Intent i = getIntent();
        username = (String) i.getSerializableExtra("userName");
        pollId = (String) i.getSerializableExtra("pollId");

        storeDataInArrays();

        recyclerView = findViewById(R.id.recyclerView3);

        showAnswerAdapter = new ShowAnswerAdapter(ShowAnswersUserActivity.this, questions, answers, dates, username, null, false);
        recyclerView.setAdapter(showAnswerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowAnswersUserActivity.this));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAnswersUserActivity.class);
                startActivity(intent);
            }
        });
    }

    void storeDataInArrays() {
        Cursor cursor = DBAnswers.readAllDataByPollIDAndUsername(pollId, username);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                questions.add(cursor.getString(2));
                answers.add(cursor.getString(3));
                dates.add(cursor.getString(4));
            }
        }
    }
}