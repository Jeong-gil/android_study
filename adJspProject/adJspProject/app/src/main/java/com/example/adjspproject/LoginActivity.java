package com.example.adjspproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import Network.LoginAction;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    TextInputEditText edit_id, edit_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_id = (TextInputEditText) findViewById(R.id.edit_id);
        edit_pwd = (TextInputEditText) findViewById(R.id.edit_pwd);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = edit_id.getText().toString().trim();
                String userPassword = edit_pwd.getText().toString().trim();

                new LoginAction(LoginActivity.this).execute(userID, userPassword);
            }
        });
    }
}