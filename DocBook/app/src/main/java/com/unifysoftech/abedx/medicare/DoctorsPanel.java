package com.unifysoftech.abedx.medicare;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsPanel extends AppCompatActivity {
    private String KEY_docId = "DoctorId";
    private String KEY_docName = "DoctorName";
    private String KEY_docSpecility = "docSpecialsation";
    private String KEY_docLocation = "docLocation";
    private String KEY_docOtherDetails = "docOtherDetails";
    private String KEY_docFree = "docFreeTime";
    private String KEY_HospitalName = "HospitalName";
    private String KEY_HospitalID = "HospitalID";
    private String URL1 = "http://10.0.2.2/androidlogin/doctorRegistration.php";
    private String URL2 = "http://10.0.2.2/androidlogin/fetchHospitals.php";
    private EditText doc_ID;
    private EditText doc_Name;
    private EditText doc_Specility;
    private EditText doc_loca;
    private EditText doc_Otherdetails;
    private EditText doc_free;
    private TextView HospitalName;
    public Spinner hospSpinner;
    public List<HospitalNames> arralistHos = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_panel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getXML();
        //get the names set in the login screen for the doctor using a bundle
      //  Bundle DocBundle = getIntent().getExtras();
       // String setname = DocBundle.getString("DOCTOR NAME");
       // doc_Name.setText(setname);
        //method to list all the hospitals
        showHospitals();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getXML() {
        doc_ID = (EditText) findViewById(R.id.editDocID);
        doc_Name = (EditText) findViewById(R.id.editDocName);
        doc_loca = (EditText) findViewById(R.id.editdocLocation);
        doc_Specility = (EditText) findViewById(R.id.editSpeciality);
        doc_Otherdetails = (EditText) findViewById(R.id.editOtherDetails);
        doc_free = (EditText) findViewById(R.id.editDay_Time);
        HospitalName = (TextView) findViewById(R.id.HOSPITALNAME);
       // hospSpinner = (Spinner) findViewById(R.id.spinnerHospital);
        hospSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                MessageClass.message(getApplicationContext(), selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showHospitals() {
        final ProgressDialog progressDialog1 = ProgressDialog.show(this, "Getting Hospitals List ....", "Please wait ...", false, false);
        final JsonObjectRequest hospitalsjsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("Mombasa County");
                    HospitalNames ghospitalName = null;
                    arralistHos = new ArrayList<HospitalNames>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        ghospitalName = new HospitalNames();
                        ghospitalName.setHosptalName(jsonObject1.getString(KEY_HospitalName));
                        arralistHos.add(ghospitalName);
                        progressDialog1.dismiss();

                        Log.d("HOSPITAL", jsonObject1.getString(KEY_HospitalID).toString());

                    }
                    HospitalsCustomList hospitalsCustomList = new HospitalsCustomList(getApplicationContext(), arralistHos);
                    hospSpinner.setAdapter(hospitalsCustomList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        MessageClass.message(getApplicationContext(), volleyError.toString());
                        progressDialog1.dismiss();
                    }
                });
        //APP_CONFIG.getInstance().addToReqQueue(hospitalsjsonObjectRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(hospitalsjsonObjectRequest);
    }

    public void btnRegisterDoc(View view) {
        register();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void register() {

        final String docId = doc_ID.getText().toString().trim();
        final String docName = doc_Name.getText().toString().trim();
        final String docLocation = doc_loca.getText().toString();
        final String docSpecility = doc_Specility.getText().toString();
        final String docOtherDetails = doc_Otherdetails.getText().toString();
        final String docfree = doc_free.getText().toString().trim();


        if (docId.equals("") ) {
            doc_ID.setError("Please Enter the Doctor id");
        }else if(docName.equals(""))
        {
            doc_Name.setError("Please Enter the Doctor Name");
        }
        else if(docLocation.equals(""))
        {
          doc_loca.setError("Please enter the doctor location");
        }
        else if(docSpecility.equals(""))
        {
           doc_Specility.setError("Please enter your doctor Specilisation");
        }

        else {
            Log.d("VAlues", docId + docName + docLocation + docSpecility + docOtherDetails + docfree);
            final ProgressDialog progressDialog = ProgressDialog.show(this, "Registering Doctor ....", "Please wait ...", false, false);
            StringRequest docstringRequest = new StringRequest(Request.Method.POST, URL1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s.trim().equals("Doctor Registered Succefully")) {
                                MessageClass.message(getApplicationContext(), "Registered");
                                progressDialog.dismiss();
                            } else if (s.trim().equals("Not Registered")) {
                                MessageClass.message(getApplicationContext(), "Not Registerd");
                                progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_docId, docId);
                    params.put(KEY_docName, docName);
                    params.put(KEY_docSpecility, docSpecility);
                    params.put(KEY_docLocation, docLocation);
                    params.put(KEY_docOtherDetails, docOtherDetails);
                    params.put(KEY_docFree, docfree);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(docstringRequest);
            doc_ID.setText("");
            doc_Name.setText("");
            doc_Specility.setText("");
            doc_loca.setText("");
            doc_Otherdetails.setText("");
            doc_free.setText("");

        }


    }

}
