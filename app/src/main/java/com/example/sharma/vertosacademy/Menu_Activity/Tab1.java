package com.example.sharma.vertosacademy.Menu_Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sharma.vertosacademy.R;

/**
 * Created by sharma on 3/26/2017.
 */

public class Tab1 extends Fragment {
    TextView txt1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View view = inflater.inflate(R.layout.tab1, container, false);
        txt1 = (TextView)view.findViewById(R.id.textView);

        return view;
    }
}
