package com.cookandroid.dbexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Network.NetworkGet;
import Network.NetworkInsert;

public class MainActivity extends AppCompatActivity {

    private Button btn_search, refreshBtn, addBtn;
    private EditText edt_search;
    private ListView listView;
    private Custom_Adapter adapter;
    private String searchText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = (Button) findViewById(R.id.btn_search);
        edt_search = (EditText) findViewById(R.id.edt_search);

        listView = findViewById(R.id.listView);
        adapter = new Custom_Adapter(MainActivity.this, R.layout.adapter_userinfo, new ArrayList<UserInfo>());
        listView.setAdapter(adapter);

        new NetworkGet((Custom_Adapter)listView.getAdapter()).execute("");

        btn_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchText = edt_search.getText().toString();
                new NetworkGet((Custom_Adapter)listView.getAdapter()).execute(searchText);
            }
        });

        refreshBtn = findViewById(R.id.btnRefresh);
        refreshBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new NetworkGet((Custom_Adapter)listView.getAdapter()).execute("");
            }
        });

        addBtn = findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final View v = getLayoutInflater().inflate(R.layout.dialog_add,null);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("멤버 추가")
                        .setView(v)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String id = ((EditText)v.findViewById(R.id.edtID)).getText().toString();
                                String name = ((EditText)v.findViewById(R.id.edtName)).getText().toString();
                                String phone = ((EditText)v.findViewById(R.id.edtPhone)).getText().toString();
                                String grade = ((EditText)v.findViewById(R.id.edtGrade)).getText().toString();

                                new NetworkInsert(adapter).execute(id,name,phone,grade);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

    }
}
