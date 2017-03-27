package com.example.sharma.vertosacademy.Account_files;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharma.vertosacademy.Drawer_Activity.MainPage;
import com.example.sharma.vertosacademy.ProgramData;
import com.example.sharma.vertosacademy.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
//import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginPage extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    Typeface typeface;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final int REQUEST_SIGNUP = 0;
    EditText _username, _passwordText;
    Button _loginButton;
    //// facebook intializing process//////
    LoginButton FBbutton;
    SignInButton Googlebutton;
    TextView signupButton;
    CallbackManager mCallbackManager;
    AccessTokenTracker mAccessTokenTracker;
    ////////google login process /////////
    private static final String TAG = "LoginPage";

    //request code for for resolution involving signin
    private static final int RC_SIGN_IN_ = 9001;
    //request code for sharing post on user profile
    private static final int RC_SHARE_DIALOG = 8031;
    //key for persisting variable as saved instance state
    public static final String KEY_IS_RESOLVING = "is_resolving";
    public static final String KEY_SHOULD_RESOLVE = "should_resolve";
    public static final String URL = "http://plus.google.com/u/0/+DelayroyStudios/post";
    public static final String LAVEL_READ_MORE = "READ_MORE";

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions googleSignInOptions;

    String name;
    String email;
    String img_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // facebook sdk initialize
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login_page);

        ////////////push notification  initialication/////////
        //OneSignal.startInit(this).init();
        typeface = Typeface.createFromAsset(getAssets(), "sangli.otf");
        _username = (EditText) findViewById(R.id.input_username);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _loginButton.setTypeface(typeface);
        signupButton = (TextView) findViewById(R.id.link_signup);
        FBbutton = (LoginButton) findViewById(R.id.btn_facebook);
        ///////////////////
        Googlebutton = (SignInButton) findViewById(R.id.google_signInButton);
        googleSignInOptions = new GoogleSignInOptions.Builder(googleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();


        /////////////////facebook process/////////////////
        FBbutton.setReadPermissions(Arrays.asList("public_profile,email,user_friends,read_custom_friendlists"));
        mCallbackManager = CallbackManager.Factory.create();
        FBbutton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graph = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String name = object.getString("name");
                            String id = object.getString("id");
                            String email = object.getString("email");

                            ArrayList fId = new ArrayList();
                            ArrayList fName = new ArrayList();
                            JSONObject jsonObject = object.getJSONObject("friends");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object1 = jsonArray.getJSONObject(i);
                                fId.add(object1.getString("id"));
                                fName.add(object1.getString("name"));
                            }
                            Toast.makeText(LoginPage.this, name, Toast.LENGTH_SHORT).show();
                            SharedprefFacebook.getmInstance(LoginPage.this).saveFBinfo(id, name, email);
                            SharedprefFacebook.getmInstance(LoginPage.this).saveFacebookdata(fId.toString(), fName.toString());
                            startActivity(new Intent(LoginPage.this, MainPage.class));

                        } catch (Exception e) {
                            Toast.makeText(LoginPage.this, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fields", "id,name,email,friends");
                graph.setParameters(bundle);
                graph.executeAsync();


                ///////logout process//////////
                mAccessTokenTracker = new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                        if (currentAccessToken == null) {
                            SharedprefFacebook.getmInstance(LoginPage.this).deleteFB();
                        }
                    }
                };

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginPage.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        ///////////LOGIN PROCESS/////////
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                _username.setText("");
                _passwordText.setText("");
            }
        });

        ///////////SIGNUP PROCESS/////////
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(i, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        Googlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googlesignin();
            }
        });

    }


    private void login() {
        if (isvaldiate()) {
            return;
        }
        final String username = _username.getText().toString();
        final String password = _passwordText.getText().toString();
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...", "Loggin in.....", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, ProgramData.URL_Login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /* Log.d("vishal sharma",""+response);*/
                if (response.equals("success")) {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "login successfull", Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("LoginStatusKey", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("LoginStatus", "login");
                    editor.apply();

                    Intent i = new Intent(LoginPage.this, MainPage.class);
                    startActivity(i);
                } else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "fail to login!", Toast.LENGTH_SHORT).show();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }


    private boolean isvaldiate() {
        String email = _username.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _username.setError("valid email address");
            return true;
        } else {
            _username.setError(null);
        }

        if (password.isEmpty() && password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric ");
            return true;
        } else {
            _passwordText.setError(null);
        }


        return false;
    }

    ///////////  integrate google social logins methods////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN_) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleresult(result);
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void googlesignin() {
        Intent i = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(i, RC_SIGN_IN_);
    }

    private void googlesignout() {


    }

    private void handleresult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String img_url = account.getPhotoUrl().toString();


            sharedPreferences = getSharedPreferences("GoogleProf", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("gUName", name);
            editor.putString("gUemail", email);
            editor.putString("gUImgUrl", img_url);
            editor.apply();
            Intent i = new Intent(this, MainPage.class);
            startActivity(i);


        }
    }

    // Hide softkeybord when click on the on the screen
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










