package com.unifysoftech.abedxh.docbook.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unifysoftech.abedx.medicare.DoctorCatergoriesFragment;
import com.unifysoftech.abedx.medicare.DoctorsPanel;
import com.unifysoftech.abedx.medicare.MessageClass;
import com.unifysoftech.abedx.medicare.R;

import java.util.HashMap;
import java.util.Map;

public class Register extends Fragment {
    private static EditText editUserName;
    private static EditText editEmail;
    private static EditText editPassword;
   // private static String RegisterURL ="http://10.0.2.2/medicare/registeruser.php";
   private static String RegisterURL ="http://abedkiloo.com/medicare/registeruser.php";
    private static String Key_UserName = "uname";
    private static String Key_UserEmail = "email";
    private static String Key_UserPassword = "pass";
    private static View rootView;
    private static Button btnRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.activity_register, container, false);
        intilization();
        return rootView;
    }
    private void intilization() {
        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        //implemtation of login
        btnRegister=(Button)rootView.findViewById(R.id.btnRegister);
        editUserName = (EditText)rootView. findViewById(R.id.editTextREGUserName);
        editEmail = (EditText) rootView.findViewById(R.id.editTextREGEmail);
        editPassword = (EditText) rootView.findViewById(R.id.editTextREGPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MessageClass.message(getActivity(),"Register");
               registerPatient();
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void registerPatient() {
       // Intent intent=new Intent(getActivity(),DoctorCatergoriesFragment.class);
        //intent.putExtra("Fragment","Fragment");
        //startActivity(intent);

        final String UName = editUserName.getText().toString().trim();
        final String UEmail = editEmail.getText().toString().trim();
        final String UPassword = editPassword.getText().toString().trim();
        Log.d("LOGINDETIALS",UName+UEmail+UPassword);

        if (UName.equals("")) {
            editUserName.setError("Please Enter your User Name");
        } else if (UEmail.equals("")) {
            editEmail.setError("Please Enter your email");
        } else if (UPassword.equals("")) {
            editPassword.setError("Please enter your Password");
        } else {
            final ProgressDialog regitering=ProgressDialog.show(getActivity(),"Registering New User","Please wait .....",false,false);
            final StringRequest register = new StringRequest(Request.Method.POST, RegisterURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    if(s.trim().equals("success"))
                    {
                        MessageClass.message(getActivity(), "Registered");
                        Log.d("RESULT", s);
                        editUserName.setText("");
                        editPassword.setText("");
                        editEmail.setText("");
                        regitering.dismiss();

                         Intent intent=new Intent(getActivity(),DoctorCatergoriesFragment.class);
                        intent.putExtra("Fragment","Login");
                        startActivity(intent);
                    }
                    else if(s.trim().equals("failed"))
                    {
                       MessageClass.message(getActivity(),"Failed Try again please");
                        regitering.dismiss();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            MessageClass.message(getActivity(),"Network Error");
                            Log.d("REGISTER ERROR","Failed"+volleyError.toString());
                            regitering.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String,String> params=new HashMap<String,String>();
                    params.put(Key_UserName,UName);
                    params.put(Key_UserEmail,UEmail);
                    params.put(Key_UserPassword,UPassword);
                    return params;
                }
            };
            RequestQueue registerRequest = Volley.newRequestQueue(getActivity());
            registerRequest.add(register);
        }
    }

}
