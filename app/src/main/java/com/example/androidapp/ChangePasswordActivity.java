package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button changePassword;
    TextView backToLogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        username = (EditText) findViewById(R.id.inputUserName2);
        password = (EditText) findViewById(R.id.inputPassword2);
        repassword = (EditText) findViewById(R.id.confirmPassword2);
        changePassword = (Button) findViewById(R.id.buttonChangePassword);
        backToLogin = (TextView) findViewById(R.id.backToLogin);
        DB = new DBHelper(this);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(ChangePasswordActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == true) {
                            Boolean insert = DB.updateData(user, pass);
                            if (insert == true) {
                                Toast.makeText(ChangePasswordActivity.this, "Password changed successfuly!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Password change failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
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