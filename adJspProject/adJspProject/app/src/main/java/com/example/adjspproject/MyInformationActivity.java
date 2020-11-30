package com.example.adjspproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Network.DeleteContentByuserID;
import Network.DeleteUserByUserID;
import Network.UpdateMyInfo;

public class MyInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);

        Button myinfoBtnUpdate = (Button) findViewById(R.id.myinfoBtnUpdate);
        Button myinfoBtnDelete = (Button) findViewById(R.id.myinfoBtnDelete);

        TextView myinfoUserID = (TextView) findViewById(R.id.MyinfoUserID);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        myinfoUserID.setText("내 아이디 : " + userID);

        myinfoBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText myinfoPw = (EditText) findViewById(R.id.myinfoPw);
                EditText myinfoName = (EditText) findViewById(R.id.myinfoName);
                EditText myinfoEmail = (EditText) findViewById(R.id.myinfoEmail);

                System.out.println(userID);
                System.out.println(myinfoPw.getText().toString().trim());

                new UpdateMyInfo().execute(userID, myinfoPw.getText().toString().trim(), myinfoName.getText().toString().trim(), myinfoEmail.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "회원정보 수정 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        myinfoBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteContentByuserID().execute(userID);
                UserSession.userID = "";
                Toast.makeText(getApplicationContext(), "정상적으로 회원탈퇴 완료되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}