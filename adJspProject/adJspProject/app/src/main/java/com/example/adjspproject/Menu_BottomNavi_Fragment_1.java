package com.example.adjspproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import Network.TitleGet;

public class Menu_BottomNavi_Fragment_1 extends Fragment {

    private View v;
    private ListView listView;
//    private Custom_Adapter adapter;
    private FragAdapter fragAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View frgView = (View) inflater.inflate(R.layout.bottomnavi_fragment_1, container,false);

        listView = (ListView) frgView.findViewById(R.id.frag1ListView);

        fragAdapter = new FragAdapter(getActivity(), R.layout.adapter_content, new ArrayList<UserContents>());

        listView.setAdapter(fragAdapter);

        new TitleGet((FragAdapter)listView.getAdapter()).execute("");

        Button btn_search = (Button) frgView.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("확인");
                Toast.makeText(getContext(), "확인", Toast.LENGTH_SHORT).show();
            }
        });

        return frgView;
    }

}
