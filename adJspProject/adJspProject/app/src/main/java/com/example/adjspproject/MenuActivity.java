package com.example.adjspproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView rv_menu;
    private ArrayList<String> MenuList;
    private MenuAdapter menuAdapter;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        rv_menu = (RecyclerView) findViewById(R.id.rv_main);
        llm = new LinearLayoutManager(this);

        MenuList = new ArrayList();
        MenuList.add("TEST_THEME");
        MenuList.add("Medical");
        MenuList.add("Sports");
        MenuList.add("Trip");
        MenuList.add("Study");
        MenuList.add("Game");
        MenuList.add("Reading");
        MenuList.add("Movie");
        MenuList.add("Food");
        MenuList.add("Picture");
        MenuList.add("IT");
        MenuList.add("Job");
        MenuList.add("Etc");
        MenuList.add("Etc");
        MenuList.add("Etc");
        MenuList.add("Etc");
        MenuList.add("Etc");

        menuAdapter = new MenuAdapter(MenuList);
        rv_menu.setLayoutManager(llm);
        rv_menu.setAdapter(menuAdapter);

        menuAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String menuname) {
                intentmenu(position, menuname);
            }
        });
    }

    private void intentmenu(int position, String menuname){
        switch (menuname) {
            case "TEST_THEME" :
                Intent intentBottomNavi = new Intent(MenuActivity.this, Menu_BottomNavigation.class);
                startActivity(intentBottomNavi);
                break;
        }
    }
}