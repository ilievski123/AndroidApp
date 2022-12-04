package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup;
    TextView backToLogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.inputUserName1);
        password = (EditText) findViewById(R.id.inputPassword1);
        repassword = (EditText) findViewById(R.id.confirmPassword);
        signup = (Button) findViewById(R.id.buttonSignedUp);
        backToLogin = (TextView) findViewById(R.id.backToLogin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignupActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(SignupActivity.this, "Signed up successfuly!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignupActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "An account with this username and password already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}