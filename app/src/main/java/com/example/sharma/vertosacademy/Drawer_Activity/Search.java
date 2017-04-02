package com.example.sharma.vertosacademy.Drawer_Activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment {
    AutoCompleteTextView search;
    List<ProgramData> autocomplete_topictitle;
    //List<ProgramData> searchlist;
    String search_title;

    public static final String TAG1 = "id";
    public static final String TAG2 = "title";

    //List<ProgramDataPicker> list;
    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        search = (AutoCompleteTextView) view.findViewById(R.id.auto_search);
        autocomplete_topictitle = new ArrayList<ProgramData>();
        search_title = search.getText().toString();
        getDropdown();

        return view;
    }

    public void getDropdown() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching data...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ProgramData.DATA_SEARCH_URL_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(getActivity(), "" + response, Toast.LENGTH_SHORT).show();
                        showsearchlist(response);


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
                params.put("title", search_title);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void showsearchlist(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);
            Toast.makeText(getActivity(), "tyr ma aaya", Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProgramData pd = new ProgramData();
                pd.autocomplete_Id = jsonObject.getString(TAG1);
                pd.autocomplete_title = jsonObject.getString(TAG2);
                autocomplete_topictitle.add(pd);
                Toast.makeText(getActivity(), pd.autocomplete_title, Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /**
         * To attach image and title in gridview
         * But we have to create own CustomGrid_adapter java ]class
         */

    }


}




