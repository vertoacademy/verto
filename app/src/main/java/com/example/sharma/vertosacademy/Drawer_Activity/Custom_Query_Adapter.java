package com.example.sharma.vertosacademy.Drawer_Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;

import java.util.List;

/**
 * Created by sharma on 3/28/2017.
 */

public class Custom_Query_Adapter extends BaseAdapter{
    Context mContext;
    List<ProgramData> queries;

    public Custom_Query_Adapter(Context mContext, List<ProgramData> queries) {
        this.mContext = mContext;
        this.queries = queries;
    }

    @Override
    public int getCount() {
        return queries.size();
    }

    @Override
    public Object getItem(int position) {
        return queries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View var_view;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            var_view = inflater.inflate(R.layout.single_query_list,null);
            TextView tv_querytitle = (TextView)var_view.findViewById(R.id.query_title);
            TextView tv_querydescription = (TextView)var_view.findViewById(R.id.query_description);
            TextView tv_querydate = (TextView)var_view.findViewById(R.id.date_time);
            TextView tv_username = (TextView)var_view.findViewById(R.id.name);
            ImageView userimage = (ImageView)var_view.findViewById(R.id.userimage);
            ProgramData pd = queries.get(position);
            tv_querytitle.setText(pd.query_title);
            tv_querydescription.setText(pd.query_description);
            tv_querydate.setText(pd.query_date);
            tv_username.setText(pd.askby);

        }
        else{
            var_view = convertView;
            TextView tv_querytitle = (TextView)var_view.findViewById(R.id.query_title);
            TextView tv_querydescription = (TextView)var_view.findViewById(R.id.query_description);
            TextView tv_querydate = (TextView)var_view.findViewById(R.id.date_time);
            TextView tv_username = (TextView)var_view.findViewById(R.id.name);
            ImageView userimage = (ImageView)var_view.findViewById(R.id.userimage);
            ProgramData pd = queries.get(position);
            tv_querytitle.setText(pd.query_title);
            tv_querydescription.setText(pd.query_description);
            tv_querydate.setText(pd.query_date);
            tv_username.setText(pd.askby);

        }
        return var_view;
    }
}
