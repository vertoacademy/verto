package com.example.sharma.vertosacademy.Program_ListActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.UrlLoader;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by sharma on 2/4/2017.
 */

public class CustomGrid_adapter extends BaseAdapter {
    private Context mContext;
    List<ProgramData> program_name;
    String url,program_id;
    ImageView iv;

    public CustomGrid_adapter(Context mContext, List<ProgramData> program_name) {
        this.mContext = mContext;
        this.program_name = program_name;

    }

    /**
     * Constructor to initialize the value which is passed from TabActivity java class */

    @Override
    public int getCount() {
        return program_name.size();
    }

    @Override
    public Object getItem(int position) {
        return program_name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View var_view_grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            //var_view_grid = convertView;
            var_view_grid = inflater.inflate(R.layout.single_grid, null);
            iv = (ImageView) var_view_grid.findViewById(R.id.iv_single_image_id);
            TextView tv = (TextView) var_view_grid.findViewById(R.id.tv_single_title);
            ProgramData pd = program_name.get(position);
            tv.setText(pd.Name);
            url = pd.ImageUrl;
            program_id = pd.Id;
            //Toast.makeText(mContext, "IMAGE : "+url, Toast.LENGTH_SHORT).show();
            Glide.with(getApplicationContext()).load(url)
                    .asBitmap().centerCrop().into(new BitmapImageViewTarget(iv) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv.setImageDrawable(circularBitmapDrawable);
                }
            });
        }else{
            var_view_grid = convertView;
            iv = (ImageView) var_view_grid.findViewById(R.id.iv_single_image_id);
            TextView tv = (TextView) var_view_grid.findViewById(R.id.tv_single_title);
            ProgramData pd = program_name.get(position);
            tv.setText(pd.Name);
        }
        return var_view_grid;
    }
}
