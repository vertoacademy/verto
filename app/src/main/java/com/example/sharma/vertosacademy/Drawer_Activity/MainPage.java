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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sharma.vertosacademy.Account_files.FriendList_fragment;
import com.example.sharma.vertosacademy.Account_files.LoginPage;
import com.example.sharma.vertosacademy.Account_files.VertosAcademy;
import com.example.sharma.vertosacademy.NoteTaker.MainNoteTaker;
import com.example.sharma.vertosacademy.Program_ListActivity.Programlist;
import com.example.sharma.vertosacademy.R;
import com.example.sharma.vertosacademy.Splashscreen;
import com.example.sharma.vertosacademy.Userdetail;
import com.facebook.login.LoginManager;

import static com.example.sharma.vertosacademy.R.id.toolbar;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView tv_imgUrl;
    TextView txt_Uname, txt_Uemail;
    Animation push_left_in, push_left_out;
    Menu menu;
    Toolbar toolbar;
    Userdetail userdeatil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        userdeatil = new Userdetail(this);
        push_left_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_in);
        push_left_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left_out);


        ///////////////to find the view the new layout  nav header/////////////////
        View view;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu = navigationView.getMenu();
        navigationView.getBackground().setAlpha(200);


        view = navigationView.getHeaderView(0);

        tv_imgUrl = (ImageView) view.findViewById(R.id.imageurl);
        txt_Uname = (TextView) view.findViewById(R.id.userlogin);
        txt_Uemail = (TextView) view.findViewById(R.id.useremail);
        ///////////////get email ,ussername and imageurl from userdedail class for google,facebook of simple login/////////////////
        String email = userdeatil.getemail();
        String username = userdeatil.getusername();
        String imageurl = userdeatil.getimageurl();
        txt_Uemail.setText(email);
        txt_Uname.setText(username);
        Glide.with(this).load(imageurl)
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(tv_imgUrl) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                tv_imgUrl.setImageDrawable(circularBitmapDrawable);
            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Vertos Academy");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setTit(String title)
    {
        toolbar.setTitle(title);
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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id == R.id.nav_Home){
            toolbar.setTitle("Vertos Academy");
            Fragment fragprofile = new Homepage_fragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentholder, fragprofile);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_profile) {
            //toolbar.setTitle("Profile");
            Fragment fragprofile = new profile();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            /*finish();
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);*/
            if (fragmentTransaction != null) {
                toolbar.setTitle("Profile");
                fragmentTransaction.replace(R.id.fragmentholder, fragprofile);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
        else if(id == R.id.nav_stickyNotes){
            startActivity(new Intent(this, MainNoteTaker.class));
        }
        else if (id == R.id.nav_share) {
            toolbar.setTitle("Share");
            Toast.makeText(this, "Share Page", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.about_us) {
            toolbar.setTitle("About Us");
            Toast.makeText(this, "About Us Page", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.app_user) {
            toolbar.setTitle("App Users");
            Fragment fragment = new FriendList_fragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            /*finish();
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);*/
            if (fragmentTransaction != null) {
                fragmentTransaction.replace(R.id.fragmentholder, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        } else if (id == R.id.nav_logout) {
            //Toast.makeText(getApplicationContext(),"chal gya",Toast.LENGTH_LONG).show();
            userdeatil.logout();
            LoginManager.getInstance().logOut();

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


