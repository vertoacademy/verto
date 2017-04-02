package com.example.sharma.vertosacademy.Drawer_Activity;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharma on 3/24/2017.
 */

public class Custom_Home_Adapter extends BaseAdapter {

    Context context;
    String[] menu_list;


    public Custom_Home_Adapter(Context context, String[] menu_list) {
        this.context = context;
        this.menu_list = menu_list;

    }

    @Override
    public int getCount() {
        return menu_list.length;
    }

    @Override
    public Object getItem(int position) {
        return menu_list[position];
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View var_view;
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            var_view = inflater.inflate(R.layout.menu_single_grid,null);
            TextView menu_name = (TextView) var_view.findViewById(R.id.menu_name);
            ImageView menu_icon = (ImageView) var_view.findViewById(R.id.ic_menu_gride);
            String pd =  menu_list[position];
            menu_name.setText(pd);
        }
        else{
            var_view = convertView;
        }
        return var_view;
    }
}
