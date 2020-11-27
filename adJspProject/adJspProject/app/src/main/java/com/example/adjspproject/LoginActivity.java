package com.example.adjspproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.SplittableRandom;

import Network.JoinAction;
import Network.LoginAction;

public class LoginActivity extends AppCompatActivity {

    Button btn_login, btn_join;
    TextInputEditText edit_id, edit_pwd;
    AlertDialog.Builder dialog_join;

    // BackPressHandler 객체 선언, 할당
    private BackPressHandler backPressHandler = new BackPressHandler(this);

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


        btn_join = (Button) findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = getLayoutInflater().inflate(R.layout.dialog_join,null);
                Button btnJoin = (Button) view.findViewById(R.id.btnJoin);

                dialog_join = new AlertDialog.Builder(LoginActivity.this);
                dialog_join
                        .setTitle("회원가입")
                        .setView(view)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();

                btnJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userID = ((EditText)view.findViewById(R.id.edtUserID)).getText().toString().trim();
                        String userPassword = ((EditText)view.findViewById(R.id.edtUserPassword)).getText().toString().trim();
                        String userName = ((EditText)view.findViewById(R.id.edtUserName)).getText().toString().trim();
                        String userEmail = ((EditText)view.findViewById(R.id.edtUserEmail)).getText().toString().trim();
                        String userGender = "";
                        int genderOk = -1;
                        RadioGroup rgUserGender = (RadioGroup) view.findViewById(R.id.rgUserGender);
                        switch (rgUserGender.getCheckedRadioButtonId()) {
                            case R.id.rbUserGenderM:
                                userGender = "남자";
                                genderOk = 1;
                                break;
                            case R.id.rbUserGenderW:
                                userGender = "여자";
                                genderOk = 1;
                                break;
                            default:
                                System.out.println("성별을 선택하세요");
                                Toast.makeText(LoginActivity.this, "성별을 선택하세요", Toast.LENGTH_SHORT).show();
                        }

                        if (genderOk == 1) {
                            System.out.println("userID : " + userID);
                            System.out.println("userPassword : " + userPassword);
                            System.out.println("userName : " + userName);
                            System.out.println("userEmail : " + userEmail);
                            System.out.println("userGender : " + userGender);
                            System.out.println("회원가입실행");

                            new JoinAction(LoginActivity.this, dialog_join).execute(userID, userPassword, userName, userEmail, userGender);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {

        // Default
        backPressHandler.onBackPressed();
    }
}