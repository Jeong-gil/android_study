package com.example.adjspproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedContentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_content_view);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");

        TextView tv = findViewById(R.id.tv_content);
        tv.setText(content);
    }
}