package com.example.adjspproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FragAdapterMycontent extends BaseAdapter {

    private Activity fragAct;
    private LayoutInflater layoutInflater;
    private ArrayList<UserContents> userContentsArr;
    int listLayout;

    public FragAdapterMycontent(Activity fragAct, int listLayout, ArrayList<UserContents> userContentsArr) {

        this.fragAct = fragAct;
        this.listLayout = listLayout;
        this.userContentsArr = userContentsArr;
        this.layoutInflater = (LayoutInflater)fragAct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setDatasMycontent(ArrayList<UserContents> userContentsArr) {
        this.userContentsArr = userContentsArr;
    }

    @Override
    public int getCount() {
        return userContentsArr.size();
    }

    @Override
    public Object getItem(int i) {
        return userContentsArr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)  view = layoutInflater.inflate(listLayout, viewGroup, false);

        final TextView tvFragItem = view.findViewById(R.id.my_title);
        tvFragItem.setText(userContentsArr.get(i).title);

        return view;
    }
}
