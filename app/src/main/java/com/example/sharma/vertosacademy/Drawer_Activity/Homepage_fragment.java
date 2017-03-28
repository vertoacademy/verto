package com.example.sharma.vertosacademy.Drawer_Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Animator;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.Menu_Activity.Menu_Activity;
import com.example.sharma.vertosacademy.NoteTaker.MainNoteTaker;
import com.example.sharma.vertosacademy.NoteTaker.NoteActivity;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.Program_ListActivity.Programlist;
import com.example.sharma.vertosacademy.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homepage_fragment extends Fragment {
    Button bt_tutorial;
    Animation blink;
    GridView menu;
    List<ProgramData> menu_list;
    ProgramData pd;
    Button main,profile;
    String str1,str2,str3;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1;


    public Homepage_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        menu_list = new ArrayList<ProgramData>();
        pd = new ProgramData();
        menu = (GridView) view.findViewById(R.id.grid_menu);
        main = (Button) view.findViewById(R.id.home_btn);
        profile = (Button) view.findViewById(R.id.profile_btna);
        bt_tutorial = (Button) view.findViewById(R.id.tutorials);
        blink = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        bt_tutorial.startAnimation(blink);
        bt_tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tutorial();
            }
        });

        materialDesignFAM = (FloatingActionMenu) view.findViewById(R.id.floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.editNote);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                //Toast.makeText(getActivity().getApplicationContext(), "Add Action Here!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), MainNoteTaker.class));
            }
        });
        /////////////GridView populate here///////////////


         String[] str = {"Notification", "Queries", "ContactUs"};

        //Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "" + str.length, Toast.LENGTH_SHORT).show();


        Custom_Home_Adapter adapter = new Custom_Home_Adapter(getActivity(), str);
        menu.setAdapter(adapter);


        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "clicked on the Queries", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), Menu_Activity.class);
                startActivity(i);
            }
        });



        return view;
    }

    public void tutorial() {
        Intent i = new Intent(getActivity(), Programlist.class);
        startActivity(i);

    }

}
