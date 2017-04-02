package com.example.sharma.vertosacademy.Program_ListActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;
import com.example.sharma.vertosacademy.Subject_topic.Topiclist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Programlist extends AppCompatActivity {
    private static final String DEFAUL_VALUE = null;
    Spinner sp_school;

    GridView var_gridview_service;

    public static final String TAG_NAME1 = "department_name";
    public static final String TAG_NAME2 = "program_name";
    public static final String TAG_NAME3 = "program_image_url";
    public static final String TAG_NAME4 = "id";
    private List<ProgramData> program_name;
    ArrayList<String> school_name;
    public String program_id;  // string for get the id of program name


    /*
          SharedPreferences sharedPreferences;
          SharedPreferences.Editor editor;
    */
    String selectedData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programlist);
        program_name = new ArrayList<ProgramData>();
        school_name = new ArrayList<String>();


        sp_school = (Spinner) findViewById(R.id.schoolname);
        // Toast.makeText(getApplication(), ""+selectedData, Toast.LENGTH_LONG).show();
        sp_school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedData = sp_school.getSelectedItem().toString();
                program_name.clear();
                var_gridview_service = (GridView) findViewById(R.id.gridview_department);
                getDataList();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getDataSpinner();
    }

    private void getDataSpinner() {
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...", "Fetching data...", false, false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ProgramData.DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();
                        //Displaying our grid
                        showspinner(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(Programlist.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    private void showspinner(JSONArray jsonArray) {
        //Looping through all the elements of json array

        for (int i = 0; i < jsonArray.length(); i++) {
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);
                //getting image url and title from json object
                school_name.add(obj.getString(TAG_NAME1));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /**
         * To attach image and title in gridview
         * But we have to create own CustomGrid_adapter java class
         */
        //CustomGrid_adapter adapter = new CustomGrid_adapter(this, school_name);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.single_layout_spinner, school_name);
        sp_school.setAdapter(adapter);
    }

    private void getDataList() {
/*


        sharedPreferences = getSharedPreferences("Schoolname", MODE_PRIVATE);
        String selected_department = sharedPreferences.getString("department_name",DEFAUL_VALUE);
*/
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...", "Fetching data...", false, false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ProgramData.DATA_URL_LIST + selectedData,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("VISHAL_MESSAGE", "" + response);
                        //Dismissing the progressdialog on response
                        loading.dismiss();
                        //Displaying our grid
                        showGrid(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    private void showGrid(JSONArray jsonArray) {
        //Looping through all the elements of json array
        //Toast.makeText(this, "Length : " + jsonArray.length(), Toast.LENGTH_SHORT).show();
        program_name = new ArrayList<ProgramData>();
        for (int i = 0; i < jsonArray.length(); i++) {
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);
                //getting image url and title from json object
                ProgramData pd = new ProgramData();
                pd.Name = obj.getString(TAG_NAME2);
                pd.ImageUrl = obj.getString(TAG_NAME3);
                pd.Id = obj.getString(TAG_NAME4);
                program_name.add(pd);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /**
         * To attach image and title in gridview
         * But we have to create own CustomGrid_adapter java class
         */
        CustomGrid_adapter adapter = new CustomGrid_adapter(this, program_name);
        //ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,program_name);
        //CustomGrid_adapter adapter = new CustomGrid_adapter(programlist.this, program_name);
        var_gridview_service.setAdapter(adapter);

        var_gridview_service.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(Programlist.this, Topiclist.class);
                //Toast.makeText(Programlist.this, "", Toast.LENGTH_SHORT).show();
                ProgramData pr = program_name.get(position); //get the arraylist index
                program_id = pr.Id;
                SharedPreferences sharedPreferences = getSharedPreferences("programidKey", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("programid", program_id);
                editor.apply();
                startActivity(i);
            }

        });


    }
}



