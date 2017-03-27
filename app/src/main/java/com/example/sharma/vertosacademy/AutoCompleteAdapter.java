package com.example.sharma.vertosacademy;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;


import static android.R.attr.resource;

/**
 * Created by sharma on 3/18/2017.
 */

public class AutoCompleteAdapter extends ArrayAdapter<ProgramDataPicker>{

    Context context;
    TextView tv_topictile;
    List<ProgramDataPicker> autocomplete_topictitle,suggestions,tempItems;

    public AutoCompleteAdapter(Context context, int resource,List<ProgramDataPicker> autocomplete_topictitle,TextView tv_topictile) {
        super(context,resource);
        this.context = context;
        this.autocomplete_topictitle = autocomplete_topictitle;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View var_view = convertView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            var_view = inflater.inflate(R.layout.single_layout_spinner,parent,false);
        }
        ProgramDataPicker pd = autocomplete_topictitle.get(position);
        if( pd != null){
            tv_topictile = (TextView) var_view.findViewById(R.id.spinnerxml);
            if(pd != null)
            pd = new ProgramDataPicker();
            tv_topictile.setText(pd.getTitle());
        }

        return  var_view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }

      Filter nameFilter = new Filter(){
          @Override
          public CharSequence convertResultToString(Object resultValue) {
               String  str = ((ProgramDataPicker) resultValue).getTitle();
              return str;
          }

          @Override
          protected FilterResults performFiltering(CharSequence constraint) {
              if ( constraint != null){
                  suggestions.clear();
                  for(ProgramDataPicker pd: tempItems){
                     if(pd.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())){
                         suggestions.add(pd);
                     }
                  }
                 FilterResults filterResults = new FilterResults();
                  filterResults.values = suggestions;
                  filterResults.count = suggestions.size();
                  return filterResults;
              } else{
                 return  new FilterResults();
              }

          }

          @Override
          protected void publishResults(CharSequence constraint, FilterResults results) {
              List<ProgramDataPicker> filterList = (ArrayList<ProgramDataPicker>) results.values;
              if (results !=null && results.count > 0){
                  clear();
                  for (ProgramDataPicker pd : filterList){
                      add(pd);
                      notifyDataSetChanged();
                  }
              }
          }
      };


}
