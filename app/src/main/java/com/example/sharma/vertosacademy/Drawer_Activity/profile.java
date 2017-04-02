package com.example.sharma.vertosacademy.Drawer_Activity;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.app.Fragment;

import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.sharma.vertosacademy.R;
import com.example.sharma.vertosacademy.Userdetail;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {
    TextView tx_password, tx_contactnumber, tx_address, topedit, tv_username, tv_email;
    RelativeLayout rl_password, rl_contact, rl_address;
    ImageView profile_pic;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String passemail,oldpass,newpass,confpass;
    Userdetail userdetail;


    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userdetail = new Userdetail(getActivity());
        String name = userdetail.getusername();
        String email = userdetail.getemail();
        String url = userdetail.getimageurl();

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tv_username = (TextView) view.findViewById(R.id.user_profile_name);
        tv_email = (TextView) view.findViewById(R.id.user_profile_email);
        profile_pic = (ImageView) view.findViewById(R.id.user_profile_photo);
        topedit = (TextView) view.findViewById(R.id.imageedit);

        tx_password = (TextView) view.findViewById(R.id.chngPass);
        tx_contactnumber = (TextView) view.findViewById(R.id.changecontact);
        tx_address = (TextView) view.findViewById(R.id.changeaddress);
        tx_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepassword();
                Snackbar.make(view, "Edit your password", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

        });
        tx_contactnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatecontact();
                Snackbar.make(view, "Edit your contact", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        tx_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateaddress();
                Snackbar.make(view, "Edit your address", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        //helo
        topedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Change your cover Image", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        tv_username.setText(name);
        tv_email.setText(email);
        Glide.with(this).load(url)
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(profile_pic) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                profile_pic.setImageDrawable(circularBitmapDrawable);
            }
        });

        return view;
    }

    public void updatepassword() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.changeprofile_password, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(promptView);

        builder.setTitle("Change Password");

        final EditText editemail = (EditText) promptView.findViewById(R.id.editTextEmail);
        final EditText editOldPass = (EditText) promptView.findViewById(R.id.editTextPass);
        final EditText editNewPass = (EditText) promptView.findViewById(R.id.editTextNewPass);
        final EditText editConfPass = (EditText) promptView.findViewById(R.id.editTextConfPass);

        passemail = editemail.getText().toString();
        oldpass = editOldPass.getText().toString();
        newpass = editNewPass.getText().toString();
        confpass =editConfPass.getText().toString();

        builder.setCancelable(false).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(getActivity(), "SAVE THE PASSWORD", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void updatecontact() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.changeprofilecontact, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(promptView);

        builder.setTitle("Change Contact Number");

        final EditText editemail = (EditText) promptView.findViewById(R.id.editTextConEmail);
        final EditText editOldPass = (EditText) promptView.findViewById(R.id.editTextOldCont);
        final EditText editNewPass = (EditText) promptView.findViewById(R.id.editTextNewCont);

        builder.setCancelable(false).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  Toast.makeText(getActivity(), "SAVE THE contact number", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void updateaddress() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.changeprofileaddress, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(promptView);

        builder.setTitle("Change Address");

         final EditText editOldAddress = (EditText) promptView.findViewById(R.id.editTextAddEmail);
         final EditText editNewPass = (EditText) promptView.findViewById(R.id.editTextNewAdd);

        builder.setCancelable(false).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //  Toast.makeText(getActivity(), "SAVE THE contact number", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    /*public void updateprofile(){
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Update profile.....", false, false);
        //StringRequest request = new StringRequest(Request.Method.POST, URL_Login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                *//* Log.d("vishal sharma",""+response);*//*
                if (response.equals("success")) {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "login successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getActivity(),MainPage.class);
                } else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "fail to login!", Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
               // map.put("username", username);
                //map.put("password", password);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);

    }*/

}
