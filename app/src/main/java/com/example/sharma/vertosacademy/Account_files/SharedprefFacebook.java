package com.example.sharma.vertosacademy.Account_files;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by sharma on 2/22/2017.
 */

public class SharedprefFacebook {
    static Context context;
    static SharedprefFacebook mInstance;
    public SharedprefFacebook(Context context){
        this.context = context;
    }

    public static synchronized SharedprefFacebook getmInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedprefFacebook(context);
        }
        return mInstance;
    }

    public void saveFBinfo(String id,String name,String email){
        SharedPreferences preferences = context.getSharedPreferences("fbdata",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fbId",id);
        editor.putString("fbName",name);
        editor.putString("fbEmail",email);
        editor.apply();
    }
    public ArrayList<String> getFBinfo(){
        SharedPreferences preferences = context.getSharedPreferences("fbdata",context.MODE_PRIVATE);
        ArrayList<String> list = new ArrayList<>();
        list.add(preferences.getString("fbId"," "));
        list.add(preferences.getString("fbName"," "));
        list.add(preferences.getString("fbEmail"," "));
        return list;
    }
    public  boolean saveFacebookdata(String userId,String userName){
        SharedPreferences preferences = context.getSharedPreferences("fbdata",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FRIEND_ID",userId.toString());
        editor.putString("FRIEND_NAME",userName.toString());
        editor.apply();
        return  true;
    }

    public  String getUserId(){
        SharedPreferences preferences = context.getSharedPreferences("fbdata",context.MODE_PRIVATE);
        return preferences.getString("FRIEND_ID",null);
    }

    public  String getUserName(){
        SharedPreferences preferences = context.getSharedPreferences("fbdata",context.MODE_PRIVATE);
        return preferences.getString("FRIEND_NAME",null);
    }

    public void deleteFB(){
        SharedPreferences preferences = context.getSharedPreferences("fbdata",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
    }
}

