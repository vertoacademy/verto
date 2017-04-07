package com.example.sharma.vertosacademy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by sharma on 3/29/2017.
 */

public class Userdetail {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    static Userdetail classObject;

    public Userdetail() {

    }

    public Userdetail(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


    public static synchronized Userdetail getObject(Context context) {
        if (classObject == null) {
            return classObject = new Userdetail(context);
        }


        return classObject;
    }


    public void setIsActive(boolean isActive) {
        editor.putBoolean("isactive", isActive);
        editor.apply();
    }

    public boolean getIsActive() {
        return sharedPreferences.getBoolean("isactive", false);
    }

    public void logout() {
        editor.clear();
        editor.apply();

    }

    public void setemail(String email) {
        editor.putString("email", email);
        editor.apply();
    }

    public String getemail() {
        return sharedPreferences.getString("email", null);
    }

    public void setusername(String username) {
        editor.putString("username", username);
        editor.apply();
    }

    public String getusername() {

        return sharedPreferences.getString("username", null);
    }


    public void setimageurl(String imageurl) {
        editor.putString("imageurl", imageurl);
        editor.apply();
    }

    public String getimageurl() {
        return sharedPreferences.getString("imageurl", null);
    }

    public void setpassword(String password) {
        editor.putString("password", password);
        editor.apply();

    }

    public String getpassword() {
        return sharedPreferences.getString("password", null);
    }

    public void setqueryId(String id) {
        editor.putString("queryid", id);
        editor.apply();
    }

    public String getqueryid() {
        return sharedPreferences.getString("queryid", null);
    }


}
