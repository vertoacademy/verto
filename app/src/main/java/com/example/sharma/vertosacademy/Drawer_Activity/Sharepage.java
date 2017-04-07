package com.example.sharma.vertosacademy.Drawer_Activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sharma.vertosacademy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sharepage extends Fragment {
    Button facebook, Google, Twitter, Share, Rate;


    public Sharepage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sharepage, container, false);
        facebook = (Button) view.findViewById(R.id.btn_facebook);
        Google = (Button) view.findViewById(R.id.btn_google);
        Twitter = (Button) view.findViewById(R.id.btn_twitter);
        Share = (Button) view.findViewById(R.id.btn_share);
        Rate = (Button) view.findViewById(R.id.btn_rating);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Vertos-Academy-1374875519236319"));
                startActivity(browserIntent);
            }
        });
        Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( "https://plus.google.com/u/0/105346138747971180180" ) );

                startActivity( browse );
            }
        });


        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    // get the Twitter app if possible
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=850065492547121155"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/vishal10012"));
                }
                getActivity().startActivity(intent);
            }
        });

        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Download Vertos Academy App from Play Store ";
                String shareSub = "Download and Review";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });


        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });


        return view;
    }

}
