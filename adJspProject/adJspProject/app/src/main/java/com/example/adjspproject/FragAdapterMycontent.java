package com.example.adjspproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Network.DeleteContentByNo;

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


        final TextView tvFragItem2 = view.findViewById(R.id.my_title_no);
        tvFragItem2.setText(userContentsArr.get(i).no);
        tvFragItem2.setVisibility(View.GONE);


        Button updateButton = view.findViewById(R.id.btn_myupdate);
        View finalView = view;
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("클릭 확인");
                System.out.println("글 번호 : " + tvFragItem2.getText().toString());

                Intent intent = new Intent(fragAct, WritingActivity.class);

                intent.putExtra("no", tvFragItem2.getText().toString());
                intent.putExtra("title", tvFragItem.getText().toString());

                fragAct.startActivity(intent);

            }
        });

        Button deleteButton = view.findViewById(R.id.btn_delete);
        View finalView1 = view;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(tvFragItem2.getText().toString());
                new DeleteContentByNo(FragAdapterMycontent.this).execute(tvFragItem2.getText().toString());

                Toast.makeText(finalView1.getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
