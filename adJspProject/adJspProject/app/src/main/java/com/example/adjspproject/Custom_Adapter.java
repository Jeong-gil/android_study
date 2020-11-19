package com.example.adjspproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Network.NetworkDelete;
import Network.NetworkUpdate;

public class Custom_Adapter extends BaseAdapter {

    private Activity mAct;
    LayoutInflater mInflater;
    ArrayList<UserInfo> mUserInfoObjArr;
    int mListLayout;

    public Custom_Adapter(Activity a, int listLayout, ArrayList<UserInfo> userInfoObjArrayListT) {

        mAct = a;
        mListLayout = listLayout;
        mUserInfoObjArr = userInfoObjArrayListT;
        mInflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setDatas(ArrayList<UserInfo> arrayList){
        mUserInfoObjArr = arrayList;
    }

    @Override
    public int getCount() {
        return mUserInfoObjArr.size();
    }

    @Override
    public Object getItem(int i) {
        return mUserInfoObjArr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)  view = mInflater.inflate(mListLayout, viewGroup, false);

        final TextView tvID = view.findViewById(R.id.tv_id);
        tvID.setText(mUserInfoObjArr.get(i).id);

        final TextView tvName = view.findViewById(R.id.tv_name);
        tvName.setText(mUserInfoObjArr.get(i).name);

        final TextView tvPhone = view.findViewById(R.id.tv_phone);
        tvPhone.setText(mUserInfoObjArr.get(i).phone);

        final TextView tvGrade = view.findViewById(R.id.tv_grade);
        tvGrade.setText(mUserInfoObjArr.get(i).grade);

        final TextView tvWriteTime = view.findViewById(R.id.tv_write_time);
        tvWriteTime.setText(mUserInfoObjArr.get(i).writeTime);

        Button updateButton = view.findViewById(R.id.btn_update);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final View view = mAct.getLayoutInflater().inflate(R.layout.dialog_add, null);
                final EditText edtID, edtName, edtPhone, edtGrade;
                edtID = (EditText) view.findViewById(R.id.edtID);
                edtName = (EditText) view.findViewById(R.id.edtName);
                edtPhone = (EditText) view.findViewById(R.id.edtPhone);
                edtGrade = (EditText) view.findViewById(R.id.edtGrade);

                edtID.setText(tvID.getText());
                edtName.setText(tvName.getText());
                edtPhone.setText(tvPhone.getText());
                edtGrade.setText(tvGrade.getText());

                edtID.setFocusable(false);

                new AlertDialog.Builder(mAct)
                        .setTitle("수정")
                        .setView(view)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = edtID.getText().toString();
                                String name = edtName.getText().toString();
                                String phone = edtPhone.getText().toString();
                                String grade = edtGrade.getText().toString();

                                new NetworkUpdate(Custom_Adapter.this).execute(id, name, phone, grade);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(false).show();
            }
        });
        Button deleteButton = view.findViewById(R.id.btn_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userID = tvID.getText().toString();
                AlertDialog.Builder ad = new AlertDialog.Builder(mAct);
                ad.setTitle("삭제하기");
                ad.setMessage("사용자 ID: "+userID+"를(을) 정말 삭제하시겠습니까?");

                ad.setNegativeButton("삭제", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                        new NetworkDelete(Custom_Adapter.this).execute(tvID.getText().toString());
                    }
                });

                ad.setPositiveButton("취소", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                        Toast.makeText(mAct, "취소하였습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                ad.show();
            }
        });

        return view;
    }
}
