package com.example.sharma.vertosacademy.Drawer_Activity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sharma.vertosacademy.R;

/**
 * Created by sharma on 3/30/2017.
 */

public class Slide_Adapter extends PagerAdapter {
    Context context;

    public Slide_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    private int[] sliderImagesId = new int[]{
            R.drawable.swipe1, R.drawable.home3, R.drawable.menulist,R.drawable.home5
    };



    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return  view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}
