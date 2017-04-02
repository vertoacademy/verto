package com.example.sharma.vertosacademy.Drawer_Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.R;
import com.example.sharma.vertosacademy.Userdetail;


public class Answer_fragment extends Fragment {
    TextView tv_problemtitle, tv_problemdesc;
    Button answer;
    RelativeLayout before, after;


    public Answer_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer_fragment, container, false);
        tv_problemtitle = (TextView) view.findViewById(R.id.problem_title);
        tv_problemdesc = (TextView) view.findViewById(R.id.problem_description);
        answer = (Button) view.findViewById(R.id.user_answer);
        before = (RelativeLayout) view.findViewById(R.id.top_layout);
        after = (RelativeLayout) view.findViewById(R.id.usereply_layout);
         /*   Retrieve the values from bundle*/
        String id = getArguments().getString("query_id");
        String title = getArguments().getString("query_title");
        String description = getArguments().getString("query_desc");


        tv_problemtitle.setText(title);
        tv_problemdesc.setText(description);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                before.setVisibility(View.GONE);
                after.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }


}
