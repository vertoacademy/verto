package com.example.sharma.vertosacademy.Drawer_Activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {
    ListView notify;
    String arr[]={"Vishal","prince","Nitin","Raman","Ajay","Mohit"};
    Toolbar toolbar;
    EditText editText;
    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        notify = (ListView)view.findViewById(R.id.notificationlist);
        Toast.makeText(getActivity(), "notifiction working", Toast.LENGTH_SHORT).show();
        ((MainPage)getActivity()).setTit("Notification");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_activated_1,arr);
        notify.setAdapter(adapter);

        //Run Time Add View Of Toolbar Icon
        /*toolbar = ((MainPage)getActivity()).toolbar;
        editText= new EditText(getActivity());
        editText.setHeight(40);
        editText.setWidth(200);
        editText.setBackgroundColor(Color.WHITE);
        toolbar.addView(editText);*/

        return view;
    }

}
