package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperUserAnswers extends SQLiteOpenHelper {

    public static final String DBName = "Login.db";

    public DBHelperUserAnswers(Context context) {
        super(context, "UserAnswers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create Table polls_answers(id TEXT primary key,nameUser TEXT,nameQuestion TEXT, answer TEXT,date TEXT, pollId TEXT,longitude TEXT, latitude TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists polls_answers");
        onCreate(MyDB);
    }


    public Boolean insertData(String id, String nameUser, String nameQuestion, String answer, String date, String pollId, String longitude, String latitude) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("nameUser", nameUser);
        contentValues.put("nameQuestion", nameQuestion);
        contentValues.put("answer", answer);
        contentValues.put("pollId", pollId);
        contentValues.put("date", date);
        contentValues.put("longitude", longitude);
        contentValues.put("latitude", latitude);
        long result = MyDB.insert("polls_answers", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    Cursor readAllDataFromPolls(){
        String query = "SELECT * FROM polls";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataFromPollsById(String id){
        String query = "SELECT * FROM polls";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM " + "polls_answers" + " where pollId = '" + id + "'" , null);
        }
        return cursor;
    }

    Cursor readAllDataFromQuestions(){
        String query = "SELECT * FROM questions";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataFromQuestionsByID(String id){
        String query = "SELECT * FROM questions WHERE pollId=id";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM " + "polls_answers" + " where nameQuestion = '" + id + "'" , null);
        }
        return cursor;
    }

}
