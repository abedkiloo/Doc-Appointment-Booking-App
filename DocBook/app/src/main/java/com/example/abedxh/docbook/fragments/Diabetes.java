package com.example.abedxh.docbook.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abedx.docbook.CustomDoctor;
import com.example.abedx.docbook.GetySetterDoctor;
import com.example.abedx.docbook.MessageClass;
import com.example.abedx.docbook.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diabetes extends Fragment {
    public ListView doctorListView;
    public static String URL = "http://mc.rapando.co.ke/src/php/requests.php";
    public static String KEY_req = "req";
    public static String Ureq = "viewDocByDisease";
    public static String Key_DiseaseKey = "disease";
    public static String Key_DiseaseName = "Diabetes";
    public List<GetySetterDoctor> list = null;
    public static String Key_doctorName = "doctorName";
    public static String Key_Hospital = "hospital";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_diabetes, container, false);
        doctorListView = (ListView) rootView.findViewById(R.id.listViewDiabetes);
        getDoctor();
        return rootView;
    }

    private void getDoctor() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("DIABETES", response);
                showDoctors();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    Log.d("StringReqErro",volleyError.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_req, Ureq);
                params.put(Key_DiseaseKey, Key_DiseaseName);
                return params;
            }
        };
        RequestQueue requestQueuesend = Volley.newRequestQueue(getActivity());
        requestQueuesend.add(stringRequest);
    }

    private void showDoctors() {
        final ProgressDialog progressDialog1 = ProgressDialog.show(getActivity(), "Getting Doctors List ....", "Please wait ...", false, false);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("disease");
                    list = new ArrayList<GetySetterDoctor>();
                    GetySetterDoctor getySetterDoctor = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        getySetterDoctor = new GetySetterDoctor();

                        getySetterDoctor.setDocName(jsonObject1.getString(Key_doctorName));
                        getySetterDoctor.setDocHospitals(jsonObject1.getString(Key_Hospital));
                        list.add(getySetterDoctor);
                        progressDialog1.dismiss();
                        // MessageClass.message(getApplicationContext(),"HOSP is"+jsonObject1.getString(KEY_HospitalName));
                        Log.d("HOSP", jsonObject1.getString(Key_doctorName));
                    }
                    CustomDoctor customDoctor = new CustomDoctor(getActivity(), list);
                    doctorListView.setAdapter(customDoctor);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        MessageClass.message(getActivity(), volleyError.toString());
                        Log.d("DOCTORJSON", volleyError.toString());
                        progressDialog1.dismiss();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

}


