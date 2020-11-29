package com.example.adjspproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView rv_menu;
    private ArrayList<String> MenuList;
    private MenuAdapter menuAdapter;
    private LinearLayoutManager llm;

    // BackPressHandler 객체 선언, 할당
    private BackPressHandler backPressHandler = new BackPressHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        rv_menu = (RecyclerView) findViewById(R.id.rv_main);
        llm = new LinearLayoutManager(this);

        MaterialToolbar toolbar = (MaterialToolbar) findViewById(R.id.materialtoolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navi_menu_more:
                System.out.println("내정보 클릭");
                Intent intentMyinfo = new Intent(MenuActivity.this, MyInformationActivity.class);
                intentMyinfo.putExtra("userID", UserSession.userID);
                startActivity(intentMyinfo);
                break;
            case R.id.navi_menu_more2:
                System.out.println("로그아웃 클릭");
                UserSession.userID = "";
                Intent intentLogout = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intentLogout);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

}