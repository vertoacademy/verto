package com.example.sharma.vertosacademy.NoteTaker;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Ashish Kumar Satyam on 3/28/2017.
 */

public class Note implements Serializable {
    private long  mDateTime;
    private String mTittle;
    private String mContent;

    public Note(long datetime,String tittle,String content)
    {
        mContent=content;
        mDateTime=datetime;
        mTittle=tittle;
    }

    public void setContent(String Content) {
        mContent=Content;
    }

    public void setDateTime(long datetime) {
        mDateTime=datetime;
    }
    public void setTittle(String tittle) {
        mTittle=tittle;
    }
    public  long getDateTime(){
        return mDateTime;
    }
    public String getTittle(){
        return mTittle;
    }
    public String getContent(){
        return mContent;
    }

    public String getDateTimeFormatted(Context context){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }


}

