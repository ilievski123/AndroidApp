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

public class MyPollsActivity extends AppCompatActivity {
    DBHelperUserAnswers DBAnswers;
    ArrayList<String> pollId;
    ArrayList<String> userName;
    PollsAdapter pollsAdapter;
    UserAnswersAdapter userAnswersAdapter;
    RecyclerView recyclerView;
    String username;
    ArrayList<String> pollName;
    DBHelperPolls DBPolls;
    ArrayList<String> pollNamePOM;
    Boolean flag;
    ArrayList<Integer> poll_time, poll_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_polls);

        recyclerView = new RecyclerView(this);
        DBAnswers = new DBHelperUserAnswers(this);
        DBPolls = new DBHelperPolls(this);
        pollId = new ArrayList<String>();
        userName = new ArrayList<String>();
        pollName = new ArrayList<String>();
        pollNamePOM = new ArrayList<String>();
        poll_btn = new ArrayList<Integer>();
        poll_time = new ArrayList<Integer>();
        flag = false;

        Intent i = getIntent();
        username = (String) i.getSerializableExtra("userName");
        Log.d("POll name: ", username);
        storeDataInArrays();
        Log.d("POll ID: ", pollId.get(0));
        for(int j = 0; j < pollId.size()-1; j++) {
            flag = false;
            for(int k = j+1; k < pollId.size(); k++) {
                if(pollId.get(j).equals(pollId.get(k))) {
                    pollId.remove(k);
                }
            }
        }

        for(int j = 0; j < pollName.size()-1; j++) {
            flag = false;
            for(int k = j+1; k < pollName.size(); k++) {
                if(pollName.get(j).equals(pollName.get(k))) {
                    pollName.remove(k);
                }
            }
        }

        for(int m = 0; m < pollName.size(); m++) {
            Log.d("POLLLLLL ", pollName.get(m));
        }
        storeDataInArraysPollName();
        storeDataInArraysPollTimeAndButton();
//        storeDataInArraysPollName();


        recyclerView = findViewById(R.id.recyclerView2);

        userAnswersAdapter = new UserAnswersAdapter(MyPollsActivity.this, pollId, pollName, poll_time, poll_btn, username);
        recyclerView.setAdapter(userAnswersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyPollsActivity.this));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShowAnswersUserActivity.class);
                startActivity(intent);
            }
        });
    }

    void storeDataInArrays() {
        Cursor cursor = DBAnswers.readAllDataByUserName(username);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                pollId.add(cursor.getString(5));
            }
        }
    }

    void storeDataInArraysPollName() {
        for(int i = 0; i < pollId.size(); i++) {
            Cursor cursor = DBPolls.readPollNameFromPollsByIdDistinct(pollId.get(i));

            if(cursor.getCount() == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            } else {
                while(cursor.moveToNext()) {
                    pollName.add(cursor.getString(0));
                }
            }
        }
    }

    void storeDataInArraysPollTimeAndButton() {
        for(int i = 0; i < pollId.size(); i++) {
            Cursor cursor = DBPolls.readAllDataFromPollsById(pollId.get(i));

            if(cursor.getCount() == 0) {
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            } else {
                while(cursor.moveToNext()) {
                    poll_time.add(cursor.getInt(2));
                    poll_btn.add(cursor.getInt(3));
                }
            }
        }
    }
}