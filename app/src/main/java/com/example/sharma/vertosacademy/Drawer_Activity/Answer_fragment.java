package com.example.sharma.vertosacademy.Drawer_Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.example.sharma.vertosacademy.Subject_topic.Custom_subjectGrid_adapter;
import com.example.sharma.vertosacademy.Subject_topic.Topiclist_fragment;
import com.example.sharma.vertosacademy.Userdetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Answer_fragment extends Fragment {

    TextView tv_problemtitle, tv_problemdesc, username, date;
    ImageView uaserimage;
    Button answer;
    ListView list;
    Userdetail userdetail;
    List<ProgramData> answerlist;
    ProgramData pd;

    String id, title, description, askby_name, date_asking;
    /*RelativeLayout before, after;

    ListView list;
    ///after layout///
    EditText useranswer;
    TextView username, date;

    Button submit_answer;


    String queryId;
    ProgramData pd;*/

    public Answer_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer_fragment, container, false);
        answerlist = new ArrayList<ProgramData>();
        userdetail = new Userdetail(getActivity());
        tv_problemtitle = (TextView) view.findViewById(R.id.problem_title);
        tv_problemdesc = (TextView) view.findViewById(R.id.problem_description);
        uaserimage = (ImageView) view.findViewById(R.id.userasked_image);
        username = (TextView) view.findViewById(R.id.name_user);
        date = (TextView) view.findViewById(R.id.asking_date);
        answer = (Button) view.findViewById(R.id.user_answer);
        list = (ListView) view.findViewById(R.id.list);


        answerlist = new ArrayList<ProgramData>();

        /*Retrieve the values from bundle*/
        id = getArguments().getString("querid");
        Toast.makeText(getActivity(), "" + id, Toast.LENGTH_SHORT).show();
        title = getArguments().getString("query_title");
        description = getArguments().getString("query_desc");
        askby_name = getArguments().getString("askername");
        date_asking = getArguments().getString("date");


        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Query_reply();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (ft != null) {
                    ft.replace(R.id.fragmentholder, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

            }
        });

        tv_problemtitle.setText(title);
        tv_problemdesc.setText(description);
        username.setText(askby_name);
        date.setText(date_asking);
        answerlist();


        return view;
    }


    public void answerlist() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching Answers......", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ProgramData.DATA_GET_ANSWER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();

                        //Displaying  our list
                        listView(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("queryId", id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void listView(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                pd = new ProgramData();
                pd.answerdesc = obj.getString("answer_description");
                pd.useranswered = obj.getString("answer_by");
                pd.answerdate = obj.getString("date_time");
                answerlist.add(pd);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Custom_Answer_Adapter adapter = new Custom_Answer_Adapter(getActivity(), answerlist);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgramData pd = answerlist.get(position);
                Toast.makeText(getActivity(), "Click on answerlist", Toast.LENGTH_SHORT).show();

            }
        });

    }
/*
    public void answer() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Saving Answer...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, ProgramData.DATA_SAVED_ANSWER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(getActivity(), "Answer submition succesfull", Toast.LENGTH_SHORT).show();
                    loading.dismiss();


                } else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "fail to save answer!", Toast.LENGTH_SHORT).show();


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
                map.put("queryId", id);
                map.put("querdesc", description);
                map.put("name", answer_uaername);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


    public void answerlist() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching Answers......", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ProgramData.DATA_GET_ANSWER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();

                        //Displaying  our list
                        listView(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("queryId", id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void listView(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                pd = new ProgramData();
                pd.answerdesc = obj.getString("answer_description");
                pd.useranswered = obj.getString("answer_by");
                pd.answerdate = obj.getString("date_time");
                answerlist.add(pd);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Custom_Answer_Adapter adapter = new Custom_Answer_Adapter(getActivity(), answerlist);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgramData pd = answerlist.get(position);
                queryId = pd.query_Id;

            }
        });

    }*/
}









