package com.example.sharma.vertosacademy.Drawer_Activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;
import com.example.sharma.vertosacademy.Userdetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;
import static android.R.attr.fragment;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Queries extends Fragment {
    ListView list;
    Button button_query;
    List<ProgramData> queries;
    Userdetail userdetail;
    ImageView blink_imag;
    Animation blink;

    public Queries() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queries, container, false);
        userdetail = new Userdetail(getActivity());
        list = (ListView) view.findViewById(R.id.query_list);
        button_query = (Button) view.findViewById(R.id.btn_add_query);
        blink_imag=(ImageView)view.findViewById(R.id.img_ask);
        blink = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        blink_imag.setAnimation(blink);
        ((MainPage) getActivity()).setTit("Queries");
        queries = new ArrayList<ProgramData>();
        Querylist();
        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Addquery();
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

    //////Methods for View Querylist/////////
    private void Querylist() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching data...", false, false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ProgramData.DATA_GET_QUESTIONS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Displaying our grid
                        listview(response);

                        //Dismissing the progressdialog on response
                        loading.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    private void listview(JSONArray response) {

        //Looping through all the elements of json array
        for (int i = 0; i < response.length(); i++) {
            JSONObject obj = null;
            //Creating a json object of the current index
            try {

                //getting json object from current index
                obj = response.getJSONObject(i);
                ProgramData pd = new ProgramData();
                pd.query_title = obj.getString("question_title");
                pd.query_date = obj.getString(("date_time"));
                pd.query_description = obj.getString("question_description");
                pd.query_Id = obj.getString("id");
                pd.askby = obj.getString("asked_by");
                pd.query_date = obj.getString("date_time");
                queries.add(pd);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        Custom_Query_Adapter adapter = new Custom_Query_Adapter(getActivity(), queries);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProgramData pd = queries.get(position);
                userdetail.setqueryId(pd.query_Id);
               // Toast.makeText(getActivity(), ""+userdetail.getqueryid(), Toast.LENGTH_SHORT).show();
                Fragment fragment = new Answer_fragment();
                Bundle b = new Bundle();
                b.putString("querid",pd.query_Id);
                b.putString("query_title", pd.query_title);
                b.putString("query_desc", pd.query_description);
                b.putString("askername", pd.askby);
                b.putString("date", pd.query_date);
                fragment.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (fragmentTransaction != null) {
                    fragmentTransaction.replace(R.id.fragmentholder, fragment);
                    fragmentTransaction.commit();
                }
            }
        });


    }


    /*public void addquestionlist() {

        final String questitle = querytitle.getText().toString();
        final String quesdescription = querydesc.getText().toString();
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Saving request...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, ProgramData.DATA_SAVED_QUESTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(getActivity(), "Question submition succesfull", Toast.LENGTH_SHORT).show();
                    loading.dismiss();

                } else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Question submition unsuccesfull", Toast.LENGTH_SHORT).show();


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

    //////Methods for View Querylist/////////
    private void Querylist() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching data...", false, false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ProgramData.DATA_GET_QUESTIONS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Displaying our grid
                        listview(response);

                        //Dismissing the progressdialog on response
                        loading.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    private void listview(JSONArray response) {

        //Looping through all the elements of json array
        for (int i = 0; i < response.length(); i++) {
            JSONObject obj = null;
            //Creating a json object of the current index
            try {
                //Toast.makeText(this, "try ma aaya", Toast.LENGTH_SHORT).show();
                //getting json object from current index
                obj = response.getJSONObject(i);
                Log.d("Vishal message", "" + obj);
                //getting image url and title from json object
                // queries.add(obj.getString(TAG_NAME1));


                ProgramData pd = new ProgramData();
                pd.query_title = obj.getString(TAG_NAME1);
                pd.query_date = obj.getString(("date_time"));
                pd.query_description = obj.getString(TAG_NAME3);
                pd.query_Id = obj.getString("id");
                pd.askby = obj.getString(TAG_NAME2);
                queries.add(pd);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        Custom_Query_Adapter adapter = new Custom_Query_Adapter(getActivity(), queries);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProgramData pd = queries.get(position);
                Fragment fragment = new Answer_fragment();
                Bundle b = new Bundle();
                b.putString("query_id",pd.query_Id);
                b.putString("query_title",pd.query_title);
                b.putString("query_desc",pd.query_description);
                fragment.setArguments(b);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (fragmentTransaction != null) {
                    fragmentTransaction.replace(R.id.fragmentholder, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });


    }*/

}
