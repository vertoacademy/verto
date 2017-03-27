package com.example.sharma.vertosacademy.Menu_Activity;

import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by sharma on 3/26/2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;


    //Constructor to the class

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }



    //Overriding method getItem
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }


}
