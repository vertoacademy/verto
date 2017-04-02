package com.example.sharma.vertosacademy.Subject_topic;

import android.content.Context;
import android.widget.Toast;

import com.example.sharma.vertosacademy.ProgramData;

import java.util.List;

/**
 * Created by sharma on 3/19/2017.
 */

public class setDATA {

    Context context;
    List<ProgramData> list;
    static setDATA classObject;
    public setDATA(Context context)
    {
        this.context=context;

    }

    public static synchronized setDATA getCustomInstance(Context context)
    {
        if(classObject==null) {
            classObject = new setDATA(context);
        }
        return classObject;
    }

    public void showSavedValues()
    {
       // Toast.makeText(context,""+list.get(0).topic_name+"  "+list.get(0).topic_description,Toast.LENGTH_LONG).show();
    }

    public void setList(List<ProgramData> list) {
        this.list = list;
    }

    public List<ProgramData> getList() {
        return list;
    }

}
