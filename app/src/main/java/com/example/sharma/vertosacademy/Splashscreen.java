package com.example.sharma.vertosacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.digits.sdk.android.Digits;
import com.example.sharma.vertosacademy.Account_files.VertosAcademy;
import com.example.sharma.vertosacademy.Drawer_Activity.MainPage;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

public class Splashscreen extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "YPsGWDCdwkAmi1AkV0UNgEt1e";
    private static final String TWITTER_SECRET = "vy5aaoe4XEUO04k9MUQeDhayxavO51pgHNOkydEtx1N7hSCMFw";

    SharedPreferences sharedPreferences;
    private static int splashInterval =4000;
    Animation zoom_out;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
        setContentView(R.layout.activity_splashscreen);
        zoom_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        logo =(ImageView)findViewById(R.id.splash_logo);
        logo.setAnimation(zoom_out);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO Auto-generated methods stub

                sharedPreferences = getSharedPreferences("LoginStatusKey", Context.MODE_PRIVATE);
                String check_simplelogin = sharedPreferences.getString("LoginStatus",null);

                if (check_simplelogin !=null && check_simplelogin.equalsIgnoreCase("login")){
                    Intent i =new Intent(Splashscreen.this,MainPage.class);
                    startActivity(i);
                    finish();
                } else{
                    Intent i =new Intent(Splashscreen.this,VertosAcademy.class);
                    startActivity(i);
                    finish();
                }

            }

        },splashInterval);



    };
}
