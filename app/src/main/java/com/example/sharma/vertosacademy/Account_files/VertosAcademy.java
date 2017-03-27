package com.example.sharma.vertosacademy.Account_files;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sharma.vertosacademy.R;

public class VertosAcademy extends AppCompatActivity {
    TextView tv_title;
    Typeface typeface;
    Button login,signup;
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertos_academy);
        tv_title =(TextView)findViewById(R.id.app_title);
        typeface= Typeface.createFromAsset(getAssets(),"Lobster.otf");
        tv_title.setTypeface(typeface);
        login = (Button)findViewById(R.id.login_btn);
        signup = (Button)findViewById(R.id.signup_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VertosAcademy.this, LoginPage.class);
                startActivityForResult(i, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(VertosAcademy.this, SignupActivity.class);
                startActivityForResult(i, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });


    }
}
