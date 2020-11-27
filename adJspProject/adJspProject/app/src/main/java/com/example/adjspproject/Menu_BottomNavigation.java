package com.example.adjspproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu_BottomNavigation extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Menu_BottomNavi_Fragment_1 menu_bottomNavi_fragment_1 = new Menu_BottomNavi_Fragment_1();
    Menu_BottomNavi_Fragment_2 menu_bottomNavi_fragment_2 = new Menu_BottomNavi_Fragment_2();
    Menu_BottomNavi_Fragment_3 menu_bottomNavi_fragment_3 = new Menu_BottomNavi_Fragment_3();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__bottom_navigation);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnavi);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, menu_bottomNavi_fragment_1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                changeFragment(item.getItemId());
                return true;
            }
        });
    }
    public void changeFragment(int title_id) {
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (title_id) {
            case R.id.botton_navi_one:
                fragmentTransaction.replace(R.id.framelayout, menu_bottomNavi_fragment_1);
                fragmentTransaction.commit();
                break;
            case R.id.botton_navi_two:
                fragmentTransaction.replace(R.id.framelayout, menu_bottomNavi_fragment_2);
                fragmentTransaction.commit();
                break;
            case R.id.botton_navi_three:
                fragmentTransaction.replace(R.id.framelayout, menu_bottomNavi_fragment_3);
                fragmentTransaction.commit();
                break;
        }
        return;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }

}