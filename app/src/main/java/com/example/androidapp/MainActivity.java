package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    TextView changePassword;
    Button login, signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.inputUserName);
        password = (EditText) findViewById(R.id.inputPassword);
        login = (Button) findViewById(R.id.buttonLogin);
        signup = (Button) findViewById(R.id.buttonSignup);
        changePassword = (TextView) findViewById(R.id.changePassword);
        DB = new DBHelper(this);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.checkusername(user);
                    Boolean checkpass = DB.checkusernamepassword(user, pass);
                    if(checkuser == false || checkpass == false) {
                        //Boolean insert = DB.insertData(user, pass);
                        Toast.makeText(MainActivity.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                    } else {
                        if(user.equals("admin") || pass.equals("admin123")) {
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                            intent.putExtra("username", user);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity((intent));
            }
        });
    }
}