package com.example.abedx.docbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PatientLogin extends AppCompatActivity {

    private static final String LOGIN_URL = "http://10.0.2.2/androidlogin/patientLogin.php";
    public static final String KEY_USERNAME = "PatUserName";
    public static final String KEY_PASSWORD = "PatPassword";

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void btnLogin(View view) {
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (username.equals("") ) {
           editTextUsername.setError("Please enter your User Name");
        }
        else if(password.equals(""))
        {
           editTextPassword.setError("Please Enter your Password");
        }
         else {
            final ProgressDialog progressDialog = ProgressDialog.show(this, "Logging in ....", "Please wait...", false, false);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("successfully Login")) {
                                MessageClass.message(getApplicationContext(),response);
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), ViewCounties.class));
                                editTextUsername.setText("");
                                editTextPassword.setText("");


                            }
                            else if (response.trim().equals("Failed.Please register if you are not a user"))
                            {
                                MessageClass.message(getApplicationContext(),response);
                                progressDialog.dismiss();
                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressDialog.dismiss();
                            MessageClass.message(getApplicationContext(),volleyError.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put(KEY_USERNAME, username);
                    params.put(KEY_PASSWORD, password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }

    public void btnRegister(View view) {
        startActivity(new Intent(this, Register.class));

    }

}
