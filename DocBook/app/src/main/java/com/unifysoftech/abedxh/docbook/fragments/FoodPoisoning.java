package com.unifysoftech.abedxh.docbook.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unifysoftech.abedx.medicare.BookAppointment;
import com.unifysoftech.abedx.medicare.CustomDoctor;
import com.unifysoftech.abedx.medicare.GetterSetterDoctor;
import com.unifysoftech.abedx.medicare.MessageClass;
import com.unifysoftech.abedx.medicare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodPoisoning extends Fragment {
    public ListView doctorListView;
    //public static String URL = "http://10.0.2.2/medicare/diseases.php?disease=Food%20Poisoning";
    public static String URL = "http://abedkiloo.com/medicare/?disease=Food%20Poisoning";
    public static String KEY_req = "req";
    public static String Ureq = "viewDocByDisease";
    public static String Key_DiseaseKey = "diseasem";
    public static String Key_DiseaseName = "Diabetes";
    public List<GetterSetterDoctor> list = null;
    public static String Key_doctorName = "doctorName";
    public static String Key_Hospital = "hospital";
    public static String Key_DOCID = "id";
    public static String Key_DISEASEID = "diseaseID";
    public static String Key_DISEASeSpecialisation = "disease";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_food_poisoning, container, false);
        doctorListView = (ListView) rootView.findViewById(R.id.listViewDiabetes);
        doctorListView = (ListView) rootView.findViewById(R.id.listViewDiabetes);
        doctorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sel = parent.getItemAtPosition(position).toString();
                //  MessageClass.message(getActivity(),"Selected is"+sel);
                startActivity(new Intent(getActivity(), BookAppointment.class));
            }
        });
        getDoctor();
        return rootView;
    }

    private void getDoctor() {
        final ProgressDialog progressDialog1 = ProgressDialog.show(getActivity(), "Getting Doctors For you  ....", "Please wait ...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("FOOD POISONING", response);
                showDoctors();
                progressDialog1.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Log.d("StringReqErro",volleyError.toString());

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put(KEY_req, Ureq);
                params.put(Key_DiseaseKey, Key_DiseaseName);
                return params;
            }
        };
        RequestQueue requestQueuesend = Volley.newRequestQueue(getActivity());
        requestQueuesend.add(stringRequest);
    }

    private void showDoctors() {
        final ProgressDialog progressDialog1 = ProgressDialog.show(getActivity(), "Getting Doctors List ....", "Please wait ...", false, false);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("disease");
                    list = new ArrayList<GetterSetterDoctor>();
                    GetterSetterDoctor getterSetterDoctor = null;
                    Log.d("Exec", "Exec");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        getterSetterDoctor = new GetterSetterDoctor();
                        // Log.d("Exec",jsonObject1.toString());
                        getterSetterDoctor.setDoctorId(jsonObject1.getString(Key_DOCID));
                        getterSetterDoctor.setDocName(jsonObject1.getString(Key_doctorName));
                        getterSetterDoctor.setDocHospitals(jsonObject1.getString(Key_Hospital));
                        getterSetterDoctor.setDISEASEID(jsonObject1.getString(Key_DISEASEID));
                        getterSetterDoctor.setDISEASESpecalisation(jsonObject1.getString(Key_DISEASeSpecialisation));
                        list.add(getterSetterDoctor);
                        progressDialog1.dismiss();
                        // MessageClass.message(getApplicationContext(),"HOSP is"+jsonObject1.getString(KEY_HospitalName));
                        Log.d("DIADOC", jsonObject1.getString(Key_doctorName));
                    }
                    CustomDoctor customDoctor = new CustomDoctor(getActivity(), list);
                    doctorListView.setAdapter(customDoctor);
                    doctorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TextView tvdoc = (TextView) view.findViewById(R.id.doctorName7);
                            TextView tvdocID = (TextView) view.findViewById(R.id.DOCTORID);
                            TextView tvdiseaseID = (TextView) view.findViewById(R.id.DISEASEID);
                            String bookingDoctor = tvdoc.getText().toString();
                            String bookingDoctorId = tvdocID.getText().toString();
                            String bookingDiseaseID = tvdiseaseID.getText().toString();
                            Bundle docNameBundle = new Bundle();
                            docNameBundle.putString("BOOKING DOCTOR NAME", bookingDoctor);
                            docNameBundle.putString("BOOKING DOCTOR ID", bookingDoctorId);
                            docNameBundle.putString("BOOKING DISEASE ID", bookingDiseaseID);
                            docNameBundle.putString("BOOKING DISEASE NAME" ,"Food Poisoning");
                            Intent myBookActivity = new Intent(getActivity(), BookAppointment.class);
                            myBookActivity.putExtras(docNameBundle);
                            startActivity(myBookActivity);
                        }
                    });


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


