package com.example.sharma.vertosacademy.Drawer_Activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;
import com.example.sharma.vertosacademy.Userdetail;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Addquery extends Fragment {
    TextView pageheading;
    EditText ed_querytitle,ed_querydescription;
    Button btn_querysubmit;

    Userdetail userdetail;
    String username;


    public Addquery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addquery, container, false);
        pageheading = (TextView)view.findViewById(R.id.heading);
        ed_querytitle = (EditText)view.findViewById(R.id.query_title);
        ed_querydescription =(EditText)view.findViewById(R.id.query_discription);
        btn_querysubmit = (Button)view.findViewById(R.id.query_submit);
        userdetail = new Userdetail(getActivity());

        ////get the username from userdetail class//////
        username= userdetail.getusername();
        btn_querysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addquestionlist();
                ed_querytitle.setText("");
                ed_querydescription.setText("");
                Fragment fragment = new Answer_fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (fragmentTransaction != null) {
                    fragmentTransaction.replace(R.id.fragmentholder, fragment);
                    fragmentTransaction.commit();
                }

            }
        });

        return view;
    }

    public void addquestionlist() {

        final String questitle = ed_querytitle.getText().toString();
        final String quesdescription = ed_querydescription.getText().toString();
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Saving request...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, ProgramData.DATA_SAVED_QUESTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(getActivity(), "Query submition succesfull", Toast.LENGTH_SHORT).show();
                    loading.dismiss();

                } else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Query submition unsuccesfull", Toast.LENGTH_SHORT).show();


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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("title", questitle);
                map.put("description", quesdescription);
                map.put("uname",username);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


}
