package com.example.abedx.docbook;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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

public class CountyHospital extends AppCompatActivity {
    private ListView hospitalListView;
    public List<HospitalNames> list = null;
    protected String URL = "http://10.0.2.2/androidlogin/fetchHospitals.php";
    private String KEY_HospitalName = "HospitalName";
    private String KEY_HospitalID = "HospitalID";
    public static final String KEY_county = "county";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_county_hospital);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intialize();

    }

    private void intialize() {
        hospitalListView = (ListView) findViewById(R.id.countycatergorylist);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getcounty();


    }

    private void getcounty() {
       // final Bundle getCounty=getIntent().getExtras();
       // final String county=getCounty.getString("COUNTY");

       final ProgressDialog countyProgress = ProgressDialog.show(this, "Fetch hospital of" , "Please wait...", false, false);
        StringRequest countyReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //MessageClass.message(getApplicationContext(),s);
               // Log.d("BUNDEL COUNTY", county);
                MessageClass.message(getApplicationContext(),s);
                 showHospitals();
                Log.d("S",s);
               // countyProgress.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                     //   countyProgress.dismiss();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
               // params.put(KEY_county, county);
               // Log.d("PARAMS",params.put(KEY_county, county));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext(), null);
        requestQueue.add(countyReq);

    }

    private void showHospitals() {
        final ProgressDialog progressDialog1 = ProgressDialog.show(this, "Getting Hospitals List ....", "Please wait ...", false, false);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("County Hospital");
                    list = new ArrayList<HospitalNames>();
                    HospitalNames ghospitalItems = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        ghospitalItems = new HospitalNames();

                        ghospitalItems.setHospitalID(jsonObject1.getString(KEY_HospitalID));
                        ghospitalItems.setHosptalName(jsonObject1.getString(KEY_HospitalName));
                        list.add(ghospitalItems);
                        progressDialog1.dismiss();
                        // MessageClass.message(getApplicationContext(),"HOSP is"+jsonObject1.getString(KEY_HospitalName));
                        Log.d("HOSP", jsonObject1.getString(KEY_HospitalName));
                    }
                    CountyHospitalCSList countyHospitalCSList = new CountyHospitalCSList(getApplicationContext(), list);
                    hospitalListView.setAdapter(countyHospitalCSList);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }


}
