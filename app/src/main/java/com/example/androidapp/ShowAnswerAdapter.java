package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowAnswerAdapter extends RecyclerView.Adapter<ShowAnswerAdapter.MyViewHolder> {
    Context context;
    ArrayList question_id, answer, poll_time, users;
    String userName;
    DBHelperUserAnswers DBAnswers;
    Boolean hasAdminClicked;

    ShowAnswerAdapter(Context context, ArrayList question_id, ArrayList answer, ArrayList poll_time, String userName, ArrayList users, Boolean hasAdminClicked) {
        this.context = context;
        this.question_id = question_id;
        this.answer = answer;
        this.poll_time = poll_time;
        this.userName = userName;
        this.users = users;
        this.hasAdminClicked = hasAdminClicked;
    }

    @NonNull
    @Override
    public ShowAnswerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow_answers, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAnswerAdapter.MyViewHolder holder, int position) {
        holder.question_id_txt.setText(String.valueOf(question_id.get(position)));
        if(!hasAdminClicked) {
            holder.userName_txt.setText(userName);
        } else if(hasAdminClicked) {
            holder.userName_txt.setText(String.valueOf(users.get(position)));
        }
        holder.answer_txt.setText(String.valueOf(answer.get(position)));
        holder.poll_time_txt.setText(String.valueOf(poll_time.get(position)));
    }

    @Override
    public int getItemCount() {
        return question_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question_id_txt, answer_txt, poll_time_txt, userName_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_id_txt = itemView.findViewById(R.id.question_name);
            answer_txt = itemView.findViewById(R.id.answer);
            poll_time_txt = itemView.findViewById(R.id.poll_time);
            userName_txt = itemView.findViewById(R.id.user);
        }
    }

}

