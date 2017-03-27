package com.example.sharma.vertosacademy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sharma on 3/4/2017.
 */

   public class subjectlistAdapter extends RecyclerView.Adapter<subjectlistAdapter.Myview>{
    Context context;
    List<ProgramData> data;

    @Override
    public Myview onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Myview holder, int position) {
       // holder.tv.setText();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  class Myview extends RecyclerView.ViewHolder{
          TextureView tv,tx;
        public Myview(View itemView) {
            super(itemView);
           // tv = itemView.findViewById()
        }
    }
}
