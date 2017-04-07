package com.example.sharma.vertosacademy.Drawer_Activity;


import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Animator;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toolbar;

import com.example.sharma.vertosacademy.Account_files.FriendList_fragment;
import com.example.sharma.vertosacademy.Menu_Activity.Menu_Activity;
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

import static com.google.android.gms.R.id.toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homepage_fragment extends Fragment {
    Button bt_tutorial;
    Animation blink;
    GridView menu;
    List<ProgramData> menu_list;
    ProgramData pd;
    Button main, profile;

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
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPageAndroid);
        Slide_Adapter adapterView = new Slide_Adapter(getActivity());
        mViewPager.setAdapter(adapterView);
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


        /////////////GridView populate here///////////////


        String[] str = {"Notification", "Queries", "ContactUs", "HR Interview"};
        Integer[] img = {R.drawable.notification, R.drawable.query, R.drawable.contactus,R.drawable.ic_interview};


        Custom_Home_Adapter adapter = new Custom_Home_Adapter(getActivity(), str, img);
        menu.setAdapter(adapter);


        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0) {
                    Fragment fragment = new Notification();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    if (fragmentTransaction != null) {
                        fragmentTransaction.replace(R.id.fragmentholder, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                } else if (id == 1) {
                    Fragment fragment = new Queries();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    if (fragmentTransaction != null) {
                        fragmentTransaction.replace(R.id.fragmentholder, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } else if (id == 2) {
                    Toast.makeText(getActivity(), "Click on Contact Us menu", Toast.LENGTH_SHORT).show();
                } else if (id == 3) {
                    Toast.makeText(getActivity(), "Click on interview menu", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }

    public void tutorial() {
        Intent i = new Intent(getActivity(), Programlist.class);
        startActivity(i);

    }


}