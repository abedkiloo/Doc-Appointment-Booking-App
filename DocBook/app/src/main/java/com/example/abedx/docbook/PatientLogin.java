package com.example.abedx.docbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PatientLogin extends AppCompatActivity {

    private static final String LOGIN_URL = "http://10.0.2.2/androidlogin/login.php";
   // private static final String LOGIN_URL = "http://abed.rapando.co.ke/androidlogin/login.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

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
        //       final ProgressDialog progressDialog = ProgressDialog.show(getApplicationContext(), "Logging in ....", "Please wait...", false, false);
        startActivity(new Intent(getApplicationContext(), ViewHospital.class));
       // progressDialog.dismiss();
//        final String username = editTextUsername.getText().toString().trim();
//        final String password = editTextPassword.getText().toString().trim();
//        if (username.equals("") && password.equals("")) {
//            Toast.makeText(this, "Please enter your username and password combination", Toast.LENGTH_LONG).show();
//        } else {
//            final ProgressDialog progressDialog = ProgressDialog.show(this, "Logging in ....", "Please wait...", false, false);
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            Toast.makeText(PatientLogin.this, response, Toast.LENGTH_LONG).show();
//                            if (response.trim().equals("success")) {
//                                startActivity(new Intent(getApplicationContext(), ViewHospital.class));
//                                progressDialog.dismiss();
//
//                            }
//                        }
//
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            Toast.makeText(PatientLogin.this, volleyError.toString(), Toast.LENGTH_LONG).show();
//                            progressDialog.dismiss();
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//
//                    params.put(KEY_USERNAME, username);
//                    params.put(KEY_PASSWORD, password);
//                    return params;
//                }
//            };
//
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }

    }

    public void btnRegister(View view) {
        startActivity(new Intent(this, Register.class));

    }

}
