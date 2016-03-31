package com.unifysoftech.abedxh.docbook.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.unifysoftech.abedx.medicare.DoctorCatergoriesFragment;
import com.unifysoftech.abedx.medicare.R;

public class PatientLogin extends Fragment {

    private static final String LOGIN_URL = "http://mc.rapando.co.ke/src/php/requests.php";
    public static final String KEY_USERNAME = "uname";
    public static final String KEY_PASSWORD = "pass";
    public static final String KEY_REQ = "req";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;
    private Button btnRegister;
    public View rootView;
    public static SharedPreferences shredSharedPreferences;
    public static SharedPreferences.Editor shredEditror;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_login, container, false);
        editTextUsername = (EditText) rootView.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) rootView.findViewById(R.id.editTextPassword);
        shredSharedPreferences = this.getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        shredEditror = shredSharedPreferences.edit();
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        btnRegister=(Button)rootView.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        return rootView;
    }


    public void Login() {
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String Ureq = "patientLogin";

        if (username.equals("")) {
            editTextUsername.setError("Please enter your User Name");
        } else if (password.equals("")) {
            editTextPassword.setError("Please Enter your Password");
        } else {
            shredEditror.putString("SETUSER", username);
            shredEditror.putString("PASSWORD", password);
            shredEditror.commit();
            startActivity(new Intent(getActivity(), DoctorCatergoriesFragment.class));
//            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Logging in ....", "Please wait...", false, false);
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            if (response.trim().equals("successfully Login")) {
//                                MessageClass.message(getActivity(), response);
//                                progressDialog.dismiss();
//                                startActivity(new Intent(getActivity(), ViewCounties.class));
//                                editTextUsername.setText("");
//                                editTextPassword.setText("");
//                                if (response.equals("successfully Login")) {
//                                    shredEditror.putString("SETUSER", username);
//                                    shredEditror.putString("PASSWORD", password);
//                                    shredEditror.commit();
//
//                                }
//                            } else if (response.trim().equals("Failed.Please register if you are not a user")) {
//                                MessageClass.message(getActivity(), response);
//                                progressDialog.dismiss();
//                                    shredEditror.putString("SETUSER", "you have not login");
//                                    shredEditror.putString("PASSWORD", "you have not login ");
//                                    shredEditror.commit();
//                            }
//                        }
//
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError volleyError) {
//                            progressDialog.dismiss();
//                            MessageClass.message(getActivity(), volleyError.toString());
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put(KEY_USERNAME, username);
//                    params.put(KEY_PASSWORD, password);
//                    params.put(KEY_REQ, Ureq);
//                    return params;
//                }
//            };
//
//            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//            requestQueue.add(stringRequest);
        }

    }

    public void Register() {
        Intent intent = new Intent(getActivity(), DoctorCatergoriesFragment.class);
        intent.putExtra("Fragment", "Register");
        startActivity(intent);

    }

}
