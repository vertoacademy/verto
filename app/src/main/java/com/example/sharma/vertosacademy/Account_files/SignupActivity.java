package com.example.sharma.vertosacademy.Account_files;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.sharma.vertosacademy.R.id.input_fullname;

public class SignupActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;
    public static final String TAG_NAME = "user_type";

    @Bind(input_fullname) TextView _fullname;
    @Bind(R.id.input_address) TextView _address;
    @Bind(R.id.input_email) TextView _email;
    @Bind(R.id.input_contac_no) TextView _contact_no;
    @Bind(R.id.input_username) TextView _username;
    @Bind(R.id.input_password) TextView _password;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginButton;
    @Bind(R.id.user_type) Spinner _user_type;
    private ArrayList<String> selectuser_type;
    ArrayAdapter adapterusertype;
    String typyeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        selectuser_type = new ArrayList<String>();
        getDataSpinner();

        // link  send user to login Page
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginPage.class);
                startActivityForResult(i, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        //Click on Signup button
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isvaldiate()) {
                    return;
                }

                postdata();
                Intent i = new Intent(SignupActivity.this,LoginPage.class);
                startActivity(i);
                _fullname.setText("");
                _address.setText("");
                _email.setText("");
                _contact_no.setText("");
                _username.setText("");
                _password.setText("");

            }
        });

    }

    private boolean isvaldiate() {
        String fullname = _fullname.getText().toString();
        String address = _address.getText().toString();
        String email = _email.getText().toString();
        String contact = _contact_no.getText().toString();
        String username = _username.getText().toString();
        String password = _password.getText().toString();
        String usertype = _user_type.getSelectedItem().toString();


        if (fullname.isEmpty()) {
            _fullname.setError("Enter Full-name");
            return true;
        } else {
            _fullname.setError(null);
        }

        if (address.isEmpty()) {
            _address.setError("enter the address ");
            return true;
        } else {
            _address.setError(null);
        }

        if (email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _email.setError("between 4 and 10 alphanumeric ");
            return true;

        } else {
            _email.setError(null);
        }
        if (usertype.isEmpty()) {
            Toast.makeText(getApplicationContext(), "user type should be selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (username.isEmpty()) {
            _username.setError("enter username");
            return true;
        } else {
            _username.setError(null);
        }


        if (password.isEmpty() && password.length() < 4 || password.length() > 10) {

            _password.setError("between 4 and 10 alphanumeric ");
            return true;
        } else {
            _password.setError(null);
        }

        if (contact.isEmpty()) {
            _contact_no.setError("enter contact_number");
        } else {
            _contact_no.setError(null);
        }


        return false;

    }

    private void postdata() {

        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...", "Fetching data...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, ProgramData.URL_POST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    // saveusertype();
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Successfully signup", Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        )

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("fullname", _fullname.getText().toString());
                map.put("email", _email.getText().toString());
                map.put("address", _address.getText().toString());
                map.put("contact_no", _address.getText().toString());
                map.put("username", _username.getText().toString());
                map.put("password", _password.getText().toString());
                map.put("usertype", typyeID);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);

    }


    ///////////spinner fuction///////////


    private void getDataSpinner() {
        //Showing a progress dialog while our app fetches the data from url

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(ProgramData.URL_POST_type_USER_GET,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        showspinner(response);
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

    private void showspinner(JSONArray jsonArray) {
        //Looping through all the elements of json array
        for (int i = 0; i < jsonArray.length(); i++) {
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);
                Log.d("vishal_message", "" + obj);
                //getting image url and title from json object
                selectuser_type.add(obj.getString(TAG_NAME));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        adapterusertype = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, selectuser_type);
        _user_type.setAdapter(adapterusertype);

        _user_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typyeID = selectuser_type.get(position);   //get string position

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /// Hide the softkeybord when click on the on the screen
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }



}

