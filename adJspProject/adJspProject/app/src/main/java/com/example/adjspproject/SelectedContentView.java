package com.example.adjspproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class SelectedContentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_content_view);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(title);

        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setText(content);
        tv_content.setMovementMethod(new ScrollingMovementMethod());
    }
}