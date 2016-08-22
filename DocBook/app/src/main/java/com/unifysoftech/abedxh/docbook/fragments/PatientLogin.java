package com.unifysoftech.abedxh.docbook.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.unifysoftech.abedx.medicare.DoctorCatergoriesFragment;
import com.unifysoftech.abedx.medicare.MessageClass;
import com.unifysoftech.abedx.medicare.R;

import java.util.HashMap;
import java.util.Map;

public class PatientLogin extends Fragment {

    //private static final String LOGIN_URL = "http://10.0.2.2/medicare/login.php";
    private static final String LOGIN_URL = "http://abedkiloo.com/medicare/login.php";
    public static final String KEY_USERNAME = "uname";
    public static final String KEY_PASSWORD = "pass";

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
        //getting the edit text  from the view and casting them in the activity
        editTextUsername = (EditText) rootView.findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) rootView.findViewById(R.id.editTextPassword);

        //getting the button from views and casting them to the activity
        btnLogin = (Button) rootView.findViewById(R.id.btnLogin);
        btnRegister = (Button) rootView.findViewById(R.id.btnRegister);

        //shared preferences to help to store the login deails untill the user has logged out
        shredSharedPreferences = this.getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        shredEditror = shredSharedPreferences.edit();

        //handling click listener on the buttons
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        //mount the view to the activity
        return rootView;
    }

    //the login method
    public void Login() {

        //getting the values that the user entered into a string
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //confirmation that the user made an entry or not and showing appropriate information
        if (username.equals("")) {
            editTextUsername.setError("Please enter your User Name");
        } else if (password.equals("")) {
            editTextPassword.setError("Please Enter your Password");
        } else {

            //the progress dialog to show waht the application is doing at the moment
            final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Logging in ....", "Please wait...", false, false);

            // implementing the login (sending data to PHP API scripts)
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //getting the response from the library used to coonect to the database
                            if (response.trim().equals("success")) {

                                //dismiss the progress dialog after the registeration is succesful
                                progressDialog.dismiss();

                                //setting the values to the shared prefernces that were entered by th user
                                shredEditror.putString("SETUSER", username);
                                shredEditror.putString("PASSWORD", password);
                                //commting the changes to the shared preferences
                                shredEditror.commit();

                                //clear the values that where entered incase of error
                                editTextUsername.setText("");
                                editTextPassword.setText("");

                            } else if (response.trim().equals("failure")) {
                                MessageClass.message(getActivity(), "Login was not successful");
                                progressDialog.dismiss();


                                shredEditror.putString("SETUSER", username);
                                shredEditror.putString("PASSWORD", password);
                                Intent intent=new Intent(getActivity(),DoctorCatergoriesFragment.class);
                                intent.putExtra("Fragment", "Login");
                                startActivity(intent);
                                //putting default values incase the user has not registered
//                                shredEditror.putString("SETUSER", "you have not login");
//                                shredEditror.putString("PASSWORD", "you have not login ");
                                //commiting the changes that have been made
                                shredEditror.commit();
                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            //incase the login fails dismiss the progress dialog
                            progressDialog.dismiss();
                            MessageClass.message(getActivity(), "Network Error");
                        }
                    }) {

                //the methos to interact with the library by sending data to it
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    //put the key value pairs of the entered data
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_PASSWORD, password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }

    }

    public void Register() {
        //if the user has not registered send to register activity
        Intent intent = new Intent(getActivity(), DoctorCatergoriesFragment.class);
        intent.putExtra("Fragment", "Register");
        startActivity(intent);

    }

}
