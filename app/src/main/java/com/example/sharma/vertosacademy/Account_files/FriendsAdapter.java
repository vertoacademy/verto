package com.example.sharma.vertosacademy.Account_files;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sharma.vertosacademy.Account_files.Friends;
import com.example.sharma.vertosacademy.R;

import java.util.List;

/**
 * Created by sharma on 2/23/2017.
 */

public class FriendsAdapter extends BaseAdapter {
    List<Friends> arrayList;
    Context context;

    public FriendsAdapter(List arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.friendslist_holder, null);
        }
        final ImageView fImage = (ImageView) v.findViewById(R.id.frndimage);
        TextView fName = (TextView) v.findViewById(R.id.frndName);
        Friends friends = arrayList.get(position);

        fName.setText(friends.name);
        String imgUrl = "https://graph.facebook.com/" + friends.id +
                "/picture?type=large";
        Glide.with(context).load(imgUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(fImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                fImage.setImageDrawable(circularBitmapDrawable);
            }
        });
        return v;

    }
}
