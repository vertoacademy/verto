package com.example.sharma.vertosacademy.Subject_topic;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;
import com.loopeer.cardstack.CardStackView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Topiclist_fragment extends Fragment implements CardStackView.ItemExpendListener {
    //int listsize;
    List<Integer> Color_list;
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
            R.color.color_7,
            R.color.color_8,
            R.color.color_9,
            R.color.color_10,
            R.color.color_11,
            R.color.color_12,
            R.color.color_13,
            R.color.color_14,
            R.color.color_15,
            R.color.color_16,
            R.color.color_17,
            R.color.color_18,
            R.color.color_19,
            R.color.color_20,
            R.color.color_21,
            R.color.color_22,
            R.color.color_23,
            R.color.color_24,
            R.color.color_25,
            R.color.color_26
    };
    private CardStackView mStackView;
    private LinearLayout mActionButtonContainer;
    private TestStackAdapter mTestStackAdapter;

    private List<ProgramData> topiclist;
    public static final String TAG1 = "topic_name";
    public static final String TAG2 = "content_description";
    String getsubjectid;


    public Topiclist_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topiclist_fragment, container, false);
        topiclist = new ArrayList<ProgramData>();
        Color_list =  new ArrayList<>();
        mStackView = (CardStackView) view.findViewById(R.id.stackview_main);

        //get subjectid from shared prepfences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SubjectidKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();
        getsubjectid = sharedPreferences.getString("subjectid", null);
        ContentCardView();
        return view;
    }

    public void onPreClick(View view) {
        mStackView.pre();
    }

    public void onNextClick(View view) {
        mStackView.next();
    }


    @Override
    public void onItemExpend(boolean expend) {

    }

    public void ContentCardView() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching data...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ProgramData.DATA_CONTENT_URL_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response == null){
                            Toast.makeText(getActivity(), "Content is not available", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Displaying our grid
                            showcardValue(response);
                            //Dismissing the progressdialog on response
                            loading.dismiss();
                        }


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
                params.put("subjectid", getsubjectid);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    public void showcardValue(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProgramData pd = new ProgramData();
                pd.topic_name = jsonObject.getString(TAG1);
                pd.topic_description = jsonObject.getString(TAG2);
                topiclist.add(pd);
                Color_list.add(TEST_DATAS[i]);

            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }


        //**
         /* To attach image and title in cardView
         * But we have to create own CustomCardView_adapter java ]class
         */
        setDATA.getCustomInstance(getActivity()).setList(topiclist);
        mStackView.setItemExpendListener(this);
        mTestStackAdapter = new TestStackAdapter(getActivity());
        mStackView.setAdapter(mTestStackAdapter);
        mTestStackAdapter.updateData(Color_list);


    }
}
