package com.example.sharma.vertosacademy.Subject_topic;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
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
public class Subject_list_fragment extends Fragment {
    private List<ProgramData> subjectname;
    public static final String TAG1 = "subject_name";
    public static final String TAG2 = "id";

    GridView subject_Gd;
    public String getprogram_id;
    public String send_subjectid;



    public Subject_list_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subject_list, container, false);
        subjectname = new ArrayList<ProgramData>();
        subject_Gd = (GridView) view.findViewById(R.id.sub_Grid);

        /// get the program id from Shared prefrences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("programidKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();
        getprogram_id = sharedPreferences.getString("programid", null);
        subjectGrid();
        return view;
    }


    public void subjectGrid() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching data...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ProgramData.DATA_SUBJECT_URL_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();
                        //Displaying our grid
                        showGrid(response);
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
                params.put("programId", getprogram_id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void showGrid(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProgramData programData = new ProgramData();
                programData.subject_name = jsonObject.getString(TAG1);
                programData.subject_id = jsonObject.getString(TAG2);
                subjectname.add(programData);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /**
         * To attach image and title in gridview
         * But we have to create own CustomGrid_adapter java ]class
         */
        Custom_subjectGrid_adapter adapter = new Custom_subjectGrid_adapter(getActivity(), subjectname);
        subject_Gd.setAdapter(adapter);

        subject_Gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new Topiclist_fragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (ft != null) {
                    ft.replace(R.id.subjectlist, fragment);
                    ft.addToBackStack(null);
                    ft.commit();

                }
                ProgramData pd = subjectname.get(position);
                send_subjectid = pd.subject_id;
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SubjectidKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("subjectid",send_subjectid);
                editor.commit();
                Toast.makeText(getActivity(), ""+send_subjectid, Toast.LENGTH_SHORT).show();

            }
        });

    }

}
