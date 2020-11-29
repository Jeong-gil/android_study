package com.example.adjspproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import Network.GetMycontent;

public class Menu_BottomNavi_Fragment_2 extends Fragment {

    private ListView listView2;
    private FragAdapterMycontent fragAdapterMycontent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View frgView2 = (View) inflater.inflate(R.layout.bottomnavi_fragment_2, container,false);

        listView2 = (ListView) frgView2.findViewById(R.id.fragListView2);

        fragAdapterMycontent = new FragAdapterMycontent(getActivity(), R.layout.adapter_mycontent, new ArrayList<UserContents>());

        listView2.setAdapter(fragAdapterMycontent);

        new GetMycontent((FragAdapterMycontent)listView2.getAdapter()).execute(UserSession.userID);

//        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("클릭확인");
//            }
//        });

        return frgView2;
    }
}
