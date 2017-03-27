package com.example.sharma.vertosacademy.Account_files;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharma.vertosacademy.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPassword extends Fragment {
    EditText ed_email;
    Button submit_email;


    public ForgetPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_forget_password, container, false);
        ed_email = (EditText) view.findViewById(R.id.sendemail);
        submit_email = (Button) view.findViewById(R.id.submit_email);
        return  view;
    }

}
