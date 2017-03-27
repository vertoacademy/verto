package com.example.sharma.vertosacademy.Drawer_Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sharma.vertosacademy.Account_files.FriendList_fragment;
import com.example.sharma.vertosacademy.Account_files.LoginPage;
import com.example.sharma.vertosacademy.Account_files.VertosAcademy;
import com.example.sharma.vertosacademy.Program_ListActivity.Programlist;
import com.example.sharma.vertosacademy.R;
import com.example.sharma.vertosacademy.Splashscreen;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView tv_imgUrl;
    TextView txt_Uname, txt_Uemail;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Animation push_left_in, push_left_out;
    Button searchbutton;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        push_left_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in);
        push_left_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_out);
      /*  searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchfunction();
            }
        });*/

        /////////to find the view the new layout  nav header///////
        View view;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu = navigationView.getMenu();


        view = navigationView.getHeaderView(0);

        tv_imgUrl = (ImageView) view.findViewById(R.id.imageurl);
        txt_Uname = (TextView) view.findViewById(R.id.userlogin);
        txt_Uemail = (TextView) view.findViewById(R.id.useremail);

        // String url =(SharedprefFacebook.getmInstance(this).getFBinfo().get(1));
        // txt_Uname.setText(SharedprefFacebook.getmInstance(this).getFBinfo().get(2));
        //txt_Uemail.setText(SharedprefFacebook.getmInstance(this).getFBinfo().get(3));

        sharedPreferences = getSharedPreferences("GoogleProf", MODE_PRIVATE);
        Glide.with(this).load(sharedPreferences.getString("gUImgUrl", "Profile Pic"))
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(tv_imgUrl) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                tv_imgUrl.setImageDrawable(circularBitmapDrawable);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.nav_login) {

            Intent i = new Intent(MainPage.this, LoginPage.class);
            startActivity(i);
            finish();

        }*/
        if (id == R.id.nav_profile) {
            Fragment fragprofile = new profile();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            /*finish();
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);*/
            if (fragmentTransaction != null) {
                fragmentTransaction.replace(R.id.fragmentholder, fragprofile);
                fragmentTransaction.commit();
            }
        } else if (id == R.id.about_us) {


        } else if (id == R.id.nav_offers) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_suggest) {

        } else if (id == R.id.nav_chat) {
            Fragment fragment = new FriendList_fragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            /*finish();
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);*/
            if (fragmentTransaction != null) {
                fragmentTransaction.replace(R.id.fragmentholder, fragment);
                fragmentTransaction.commit();
            }


        } else if (id == R.id.nav_logout) {
            //Toast.makeText(getApplicationContext(),"chal gya",Toast.LENGTH_LONG).show();
            SharedPreferences sharedPreferences = getSharedPreferences("LoginStatusKey", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.apply();
            editor.clear();
            editor.apply();
            Intent j = new Intent(MainPage.this, LoginPage.class);
            startActivity(j);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void searchfunction() {
        Fragment searchfrag = new Search();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
        ft.replace(R.id.fragmentholder, searchfrag);
        ft.commit();
    }
}


