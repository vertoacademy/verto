package com.example.sharma.vertosacademy.Subject_topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.ProgramDataPicker;
import com.example.sharma.vertosacademy.R;

import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by sharma on 3/18/2017.
 */

public class Custom_autocomplete_adapter extends BaseAdapter {
    Context mContext;
    List<ProgramDataPicker> autocomplete_topictitle;
    TextView tv_title;

    public Custom_autocomplete_adapter(Context mContext, List<ProgramDataPicker> autocomplete_topictitle) {
        this.mContext = mContext;
        this.autocomplete_topictitle = autocomplete_topictitle;
    }

    @Override
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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View var_view;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            var_view = inflater.inflate(R.layout.single_layout_spinner, null);
            tv_title = (TextView) var_view.findViewById(R.id.spinnerxml);
            ProgramDataPicker pr = autocomplete_topictitle.get(position);
            tv_title.setText(pr.getTitle());


        } else {
            var_view = convertView;
            tv_title = (TextView) var_view.findViewById(R.id.spinnerxml);
            ProgramDataPicker pr = autocomplete_topictitle.get(position);
            tv_title.setText(pr.getTitle());
        }
        return var_view;
    }
}
