package com.example.adjspproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import Network.GetContent;
import Network.InsertContent;
import Network.UpdateContent;

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
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        Intent intent = getIntent();
        String no = intent.getStringExtra("no");
        String title = intent.getStringExtra("title");

//        if (no == null && title == null) System.out.println("no == null : " + no + ", title == null : " + title);
//        else System.out.println("no != null : " + no + ", title != null : " + title);


        if (no != null && title != null) {
            edt_title.setText(title);
//            System.out.println("edt_title.getText().toString()" + edt_title.getText().toString());

            try {
                String contentStr = new GetContent().execute(no).get();
                edt_content.setText(contentStr);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            btn_write.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String contentNo = no;
                    String contentTitle = edt_title.getText().toString().trim();
                    String content = edt_content.getText().toString().trim();

                    System.out.println("contentNo = " + contentNo);
                    System.out.println("contentTitle = " + contentTitle);
                    System.out.println("content = " + content);

                    new UpdateContent().execute(contentNo, contentTitle, content);

                    Intent intent = new Intent(WritingActivity.this, Menu_BottomNavigation.class);
                    startActivity(intent);
                }
            });

        } else {
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

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}