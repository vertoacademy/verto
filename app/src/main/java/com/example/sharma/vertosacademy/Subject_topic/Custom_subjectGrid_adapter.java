package com.example.sharma.vertosacademy.Subject_topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;

import java.util.List;

/**
 * Created by sharma on 3/15/2017.
 */

public class Custom_subjectGrid_adapter extends BaseAdapter {
    private Context mContext;
    TextView subject_n;
    private List<ProgramData> subjectname;

    public Custom_subjectGrid_adapter(Context mContext, List<ProgramData> subjectname) {
        this.mContext = mContext;
        this.subjectname = subjectname;
    }

    @Override
    public int getCount() {
        return subjectname.size();
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

        View var_view_subject_Grid ;
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            var_view_subject_Grid = inflater.inflate(R.layout.single_subject_grid,null);
            subject_n = (TextView)var_view_subject_Grid.findViewById(R.id.sub_name);
            ProgramData pd = subjectname.get(position);
            subject_n.setText(pd.subject_name);
        }
        else{
            var_view_subject_Grid = convertView;
            subject_n = (TextView)var_view_subject_Grid.findViewById(R.id.sub_name);
            ProgramData pd = subjectname.get(position);
            subject_n.setText(pd.subject_name);
        }
        return var_view_subject_Grid;
    }
}
