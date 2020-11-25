package com.example.adjspproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import Network.InsertContent;
import Network.LoginAction;

public class WritingActivity extends AppCompatActivity {

    EditText edt_title, edt_content;
    Button btn_write, btn_cancel;
    private Menu_BottomNavi_Fragment_1 menu_bottomNavi_fragment_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        edt_title = (EditText) findViewById(R.id.edt_title);
        edt_content = (EditText) findViewById(R.id.edt_content);

        btn_write = (Button) findViewById(R.id.btn_write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = UserSession.userID;
                String contentTitle = edt_title.getText().toString().trim();
                String content = edt_content.getText().toString().trim();

                new InsertContent().execute(userID, contentTitle, content);

                Intent intent = new Intent(WritingActivity.this, Menu_BottomNavigation.class);
                startActivity(intent);
            }
        });

    }
}