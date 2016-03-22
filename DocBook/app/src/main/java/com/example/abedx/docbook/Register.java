package com.example.abedx.docbook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private static Spinner spinnerDocPatient;
    private static EditText editUserName;
    private static EditText editEmail;
    private static EditText editPassword;
    private static ArrayAdapter docpatinetAdapter;
    private static String selection = null;
    private static String RegisterURL ="http://10.0.2.2/androidlogin/registerPatients.php";
    private static String Key_UserName = "PatUserName";
    private static String Key_UserEmail = "PatEmail";
    private static String Key_UserPassword = "PatPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        intilization();


    }

    private void intilization() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //implemtation of login
        editUserName = (EditText) findViewById(R.id.editTextREGUserName);
        editEmail = (EditText) findViewById(R.id.editTextREGEmail);
        editPassword = (EditText) findViewById(R.id.editTextREGPassword);
        spinnerDocPatient = (Spinner) findViewById(R.id.spinnerDocPatient);
        //intialize the adapter created from an string resource
        docpatinetAdapter = ArrayAdapter.createFromResource(this, R.array.DocorPatient, android.R.layout.simple_list_item_1);
        //set the spinner to be a drop down
        docpatinetAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        //let the spinner addapt the docpatinetAdapter
        spinnerDocPatient.setAdapter(docpatinetAdapter);
        spinnerDocPatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = parent.getItemAtPosition(position).toString();
                // Toast.makeText(getApplicationContext(), "You will registered as " + selection, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
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

    public void btnRegister(View view) {
        String doc = editUserName.getText().toString().trim();
        if (selection.trim().equals("Doctor")) {
            Intent intent = new Intent(getApplicationContext(), DoctorsPanel.class);
            Bundle bundle = new Bundle();
            bundle.putString("DOCTOR USER NAME", doc);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (selection.trim().equals("Patient")) {
            registerPatient();

        }
    }

    private void registerPatient() {
        final String UName = editUserName.getText().toString().trim();
        final String UEmail = editEmail.getText().toString().trim();
        final String UPassword = editPassword.getText().toString().trim();
        if (UName.equals("")) {
            editUserName.setError("Please Enter your User Name");
        } else if (UEmail.equals("")) {
            editEmail.setError("Please Enter your email");
        } else if (UPassword.equals("")) {
            editPassword.setError("Please enter your Psssword");
        } else {
            final ProgressDialog regitering=ProgressDialog.show(this,"Registering New User","Please wait .....",false,false);
            StringRequest register = new StringRequest(Request.Method.POST, RegisterURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    MessageClass.message(getApplicationContext(),s);

                    regitering.dismiss();
                    editUserName.setText("");
                    editPassword.setText("");
                    editEmail.setText("");
                    if(s.trim().equals("User Registered succesfully"))
                    {
                        startActivity(new Intent(getApplicationContext(),PatientLogin.class));
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            MessageClass.message(getApplicationContext(),volleyError.toString());
                            Log.d("REGISTER ERROR",volleyError.toString());
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
            RequestQueue registerRequest = Volley.newRequestQueue(getApplicationContext());
            registerRequest.add(register);
        }
    }

}
