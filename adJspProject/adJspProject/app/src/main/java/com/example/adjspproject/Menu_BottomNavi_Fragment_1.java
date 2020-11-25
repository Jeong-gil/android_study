package com.example.adjspproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Network.GetContent;
import Network.GetTitle;

public class Menu_BottomNavi_Fragment_1 extends Fragment {

    private View v;
    private ListView listView;
    private FragAdapter fragAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View frgView = (View) inflater.inflate(R.layout.bottomnavi_fragment_1, container,false);

        listView = (ListView) frgView.findViewById(R.id.frag1ListView);

        fragAdapter = new FragAdapter(getActivity(), R.layout.adapter_content, new ArrayList<UserContents>());

        listView.setAdapter(fragAdapter);

        new GetTitle((FragAdapter)listView.getAdapter()).execute("");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                Toast.makeText(getContext(), "확인", Toast.LENGTH_SHORT).show();

                TextView tvFragItem2 = view.findViewById(R.id.tv_fragItem2);
                String strNo = tvFragItem2.getText().toString();
//                Toast.makeText(getContext(), strNo, Toast.LENGTH_SHORT).show();

                try {
                    String testStr = new GetContent().execute(strNo).get();
                    System.out.println("인텐트로 넘겨줄 글 내용 : " + testStr);

                    Intent intent = new Intent(getContext(), SelectedContentView.class);

                    intent.putExtra("content",testStr);

                    startActivity(intent);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

//        Button btn_search = (Button) frgView.findViewById(R.id.btn_search);
//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("확인");
//                Toast.makeText(getContext(), "확인", Toast.LENGTH_SHORT).show();
//            }
//        });

        return frgView;
    }

}
