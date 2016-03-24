package com.example.abedxh.docbook.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abedx.docbook.MessageClass;
import com.example.abedx.docbook.R;
import com.example.abedx.docbook.ViewCounties;

import java.util.HashMap;
import java.util.Map;

public class PatientLogin extends Fragment {

    private static final String LOGIN_URL = "http://mc.rapando.co.ke/src/php/requests.php";
    public static final String KEY_USERNAME = "uname";
    public static final String KEY_PASSWORD = "pass";
    public static final String KEY_REQ = "req";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_login, container, false);
        editTextUsername = (EditText) rootView.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        btnLogin=(Button)rootView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        return rootView;
    }


    public void Login() {
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String Ureq="patientLogin";

        if (username.equals("") ) {
           editTextUsername.setError("Please enter your User Name");
        }
        else if(password.equals(""))
        {
           editTextPassword.setError("Please Enter your Password");
        }
         else {
            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Logging in ....", "Please wait...", false, false);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals("successfully Login")) {
                                MessageClass.message(getActivity(), response);
                                progressDialog.dismiss();
                                startActivity(new Intent(getActivity(), ViewCounties.class));
                                editTextUsername.setText("");
                                editTextPassword.setText("");


                            }
                            else if (response.trim().equals("Failed.Please register if you are not a user"))
                            {
                                MessageClass.message(getActivity(),response);
                                progressDialog.dismiss();
                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            progressDialog.dismiss();
                            MessageClass.message(getActivity(),volleyError.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_PASSWORD, password);
                    params.put(KEY_REQ,Ureq);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }

    }

    public void btnRegister(View view) {
        startActivity(new Intent(getActivity(), Register.class));

    }

}
