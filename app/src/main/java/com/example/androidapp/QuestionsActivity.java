package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class QuestionsActivity extends AppCompatActivity {
    EditText addQuestion, answer1, answer2, answer3, answer4;
    Button anotherQuestion;
    Button finishPoll;
    DBHelper DB;
    DBHelper2 DB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        addQuestion = (EditText)findViewById(R.id.inputQuestion);
        answer1 = (EditText)findViewById(R.id.inputAnswer1);
        answer2 = (EditText)findViewById(R.id.inputAnswer2);
        answer3 = (EditText)findViewById(R.id.inputAnswer3);
        answer4 = (EditText)findViewById(R.id.inputAnswer4);
        anotherQuestion = (Button) findViewById(R.id.buttonAddQuestion);
        finishPoll = (Button) findViewById(R.id.buttonFinishPoll);
        DB = new DBHelper(this);
        DB2 = new DBHelper2(this);
        UUID idGUID = java.util.UUID.randomUUID();

        Intent i = getIntent();
        String pollId = (String) i.getSerializableExtra("pollId");

        anotherQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addQuestion.getText().toString();
                String an1 = answer1.getText().toString();
                String an2 = answer2.getText().toString();
                String an3 = answer3.getText().toString();
                String an4 = answer4.getText().toString();
                String questionId = idGUID.toString();

                if(name.equals("") || an1.equals("") || an2.equals("")) {
                    Toast.makeText(QuestionsActivity.this, "Please enter question name and minimum of two answers!", Toast.LENGTH_SHORT).show();
                } else {
                    DB2.insertDataForQuestions(questionId, name, an1, an2, an3, an4, pollId);
                }

                Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra("pollId", pollId);
                startActivity(intent);
            }
        });

        finishPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

    }
}