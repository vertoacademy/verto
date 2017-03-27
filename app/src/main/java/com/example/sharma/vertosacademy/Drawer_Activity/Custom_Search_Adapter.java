package com.example.sharma.vertosacademy.Drawer_Activity;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;

import java.util.List;

/**
 * Created by sharma on 3/18/2017.
 */

public class Custom_Search_Adapter extends ArrayAdapter<ProgramData> {
    Context mContecxt;
    //List<ProgramData> searchlist;
    List<ProgramData> autocomplete_topictitle;
    private int viewResourceId;

    public Custom_Search_Adapter(Context context, int viewResourceId, List<ProgramData> autocomplete_topictitle) {
        super(context, viewResourceId);
        this.autocomplete_topictitle = autocomplete_topictitle;
        this.viewResourceId = viewResourceId;
    }



   /* @Override
    public int getCount() {
        return autocomplete_topictitle.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View var_view_searclist = convertView;
        if (var_view_searclist == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var_view_searclist = inflater.inflate(viewResourceId, null);

        }
        ProgramData pd = autocomplete_topictitle.get(position);
        if (pd != null) {
            TextView tv_searchtitle = (TextView) var_view_searclist.findViewById(R.id.tv_single_title);
            if (tv_searchtitle != null) {
                tv_searchtitle.setText(pd.autocomplete_title);


            }

        }
       /* LayoutInflater inflater = (LayoutInflater) mContecxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            var_view_searclist = inflater.inflate(R.layout.single_list, null);
            ProgramData pd = autocomplete_topictitle.get(position);

            TextView tv_searchtitle = (TextView) var_view_searclist.findViewById(R.id.tv_single_title);
            tv_searchtitle.setText(pd.search_title);
            TextView tv_searchurl = (TextView) var_view_searclist.findViewById(R.id.tv_single_href);
            tv_searchurl.setText(pd.search_url);
            TextView tv_search_description = (TextView) var_view_searclist.findViewById(R.id.tv_single_discription);
            tv_search_description.setText(pd.search_description);

        } else {
            var_view_searclist = convertView;
            ProgramData pd = new ProgramData();

            TextView tv_searchtitle = (TextView) var_view_searclist.findViewById(R.id.tv_single_title);
            tv_searchtitle.setText(pd.search_title);
            TextView tv_searchurl = (TextView) var_view_searclist.findViewById(R.id.tv_single_href);
            tv_searchurl.setText(pd.search_url);
            TextView tv_search_description = (TextView) var_view_searclist.findViewById(R.id.tv_single_discription);
            tv_search_description.setText(pd.search_description);
        }*/


        return var_view_searclist;
    }
}
