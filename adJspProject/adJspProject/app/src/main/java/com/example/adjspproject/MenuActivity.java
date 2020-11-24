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
        MenuList.add("BottomNavigation");
        MenuList.add("1");
        MenuList.add("2");
        MenuList.add("3");
        MenuList.add("4");
        MenuList.add("5");
        MenuList.add("6");
        MenuList.add("7");
        MenuList.add("8");
        MenuList.add("9");
        MenuList.add("10");
        MenuList.add("11");
        MenuList.add("12");
        MenuList.add("13");
        MenuList.add("14");
        MenuList.add("15");

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
            case "BottomNavigation" :
                Intent intentBottomNavi = new Intent(MenuActivity.this, Menu_BottomNavigation.class);
                startActivity(intentBottomNavi);
                break;
        }
    }
}