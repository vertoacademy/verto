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
public class Query_reply extends Fragment {
    EditText ed_query_reply;
    TextView tv_replyer_name,tv_replyer_date;
    Button btn_query_reply;

    Userdetail userdetail;
    String id,username,description;


    public Query_reply() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_query_reply, container, false);
        userdetail= new Userdetail(getActivity());
        ed_query_reply=(EditText)view.findViewById(R.id.ed_answer);
        tv_replyer_name=(TextView)view.findViewById(R.id.name_userreplyer);
        tv_replyer_date=(TextView)view.findViewById(R.id.reply_date);
        btn_query_reply=(Button)view.findViewById(R.id.reply_submit);


        //Toast.makeText(getActivity(), ""+id+""+description+username, Toast.LENGTH_SHORT).show();

        btn_query_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                answer();
                ed_query_reply.setText("");

            }
        });

        return view;
    }

    public void answer() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Saving Answer...", false, false);
        // sending data
        id =userdetail.getqueryid();
        username = userdetail.getusername();
        description = ed_query_reply.getText().toString();
        Toast.makeText(getActivity(), ""+id+""+description+username, Toast.LENGTH_SHORT).show();
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
                map.put("name", username);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

}
