package com.example.abedx.docbook;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pharmacies extends Fragment {
    public static ListView pharmaciesListView;
    public static String URL = "http://mc.rapando.co.ke/src/php/requests.php";
    public static String KEY_req = "req";
    public static String Ureq = "viewPharmacies";
    public List<GetterSetterPharmacy> list = null;
    public static String Key_Pharmacy = "name";
    public static String Key_county="county";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_pharmacies, container, false);
        pharmaciesListView = (ListView) rootView.findViewById(R.id.listViewDiabetes);
        getPharmacy();
        return rootView;
    }

    private void getPharmacy() {
        final ProgressDialog pharmacyprogress = ProgressDialog.show(getActivity(), "Fetch hospital of", "Please wait...", false, false);
        StringRequest pharmacyreq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //MessageClass.message(getApplicationContext(),s);
                // Log.d("BUNDEL COUNTY", county);
                MessageClass.message(getActivity(), s);
                showPharmacies();
                Log.d("PHARMACIES", s);
                pharmacyprogress.dismiss();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        pharmacyprogress.dismiss();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_req, Ureq);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity(), null);
        requestQueue.add(pharmacyreq);
    }

    private void showPharmacies() {
        final ProgressDialog progressDialog1 = ProgressDialog.show(getActivity(), "Getting Pharmacies List ....", "Please wait ...", false, false);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("pharmacies");
                    list = new ArrayList<GetterSetterPharmacy>();
                    GetterSetterPharmacy getySetterDoctor = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        getySetterDoctor = new GetterSetterPharmacy();

                        getySetterDoctor.setPharmacyName(jsonObject1.getString(Key_Pharmacy));
                        getySetterDoctor.setPharmacyCounty(jsonObject1.getString(Key_county));
                        list.add(getySetterDoctor);
                        progressDialog1.dismiss();
                        // MessageClass.message(getApplicationContext(),"HOSP is"+jsonObject1.getString(KEY_HospitalName));
                        Log.d("HOSP", jsonObject1.getString(Key_county));
                    }
                    PharmacyList pharmacyList=new PharmacyList(getActivity(),list);
                    pharmaciesListView.setAdapter(pharmacyList);

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


