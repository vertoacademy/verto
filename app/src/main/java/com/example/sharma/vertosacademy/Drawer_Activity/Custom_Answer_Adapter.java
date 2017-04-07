package com.example.sharma.vertosacademy.Drawer_Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;

import java.util.List;

/**
 * Created by sharma on 4/4/2017.
 */

public class Custom_Answer_Adapter extends BaseAdapter {
    Context context;
    List<ProgramData> answerlist;

    public Custom_Answer_Adapter() {

    }

    public Custom_Answer_Adapter(Context context, List<ProgramData> answerlist) {
        this.context = context;
        this.answerlist = answerlist;
    }

    @Override
    public int getCount() {
        return answerlist.size();
    }

    @Override
    public Object getItem(int position) {
        return answerlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View var_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            var_view = inflater.inflate(R.layout.single_answer_list, null);
            TextView tv_answer = (TextView) var_view.findViewById(R.id.query_description);
            TextView tv_name = (TextView) var_view.findViewById(R.id.name);
            TextView tv_date = (TextView) var_view.findViewById(R.id.date_time);
            ImageView username = (ImageView) var_view.findViewById(R.id.userimage);
            ProgramData pd = answerlist.get(position);
            tv_answer.setText(pd.answerdesc);
            tv_name.setText(pd.useranswered);
            tv_date.setText(pd.answerdate);


        } else {
            var_view = convertView;
            TextView tv_answer = (TextView) var_view.findViewById(R.id.query_description);
            TextView tv_name = (TextView) var_view.findViewById(R.id.name);
            TextView tv_date = (TextView) var_view.findViewById(R.id.date_time);
            ImageView username = (ImageView) var_view.findViewById(R.id.userimage);
            ProgramData pd = answerlist.get(position);
            tv_answer.setText(pd.answerdesc);
            tv_name.setText(pd.useranswered);
            tv_date.setText(pd.answerdate);
        }

        return var_view;
    }
}
