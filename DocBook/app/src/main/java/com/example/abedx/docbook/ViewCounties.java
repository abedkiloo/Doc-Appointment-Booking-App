package com.example.abedx.docbook;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewCounties extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] County_Names;//hold county names from the string array
    TypedArray County_Pics;//hold the interger values of the images from the string
    String[] NumberOfHospitals;
    String[] MainSites;
    List<RowItem> ListrowItems;
    ListView CountylistView;
    private String URL = "http://localhost/androidlogin/fetchHospitals.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hospital);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListrowItems = new ArrayList<RowItem>();
        County_Names = getResources().getStringArray(R.array.Counties);
        County_Pics = getResources().obtainTypedArray(R.array.CountyPics);
        NumberOfHospitals = getResources().getStringArray(R.array.NumberOFHospital);
        MainSites = getResources().getStringArray(R.array.MainSites);
        for (int i = 0; i < County_Names.length; i++) {

            RowItem pickedItems = new RowItem(
                    County_Names[i], County_Pics.getResourceId(i, -1),
                    NumberOfHospitals[i], MainSites[i]);
            ListrowItems.add(pickedItems);
        }
        CountylistView = (ListView) findViewById(R.id.CountylistView);
        CountyCustomAdapter countyCustomAdapter = new CountyCustomAdapter(this, ListrowItems);
        CountylistView.setAdapter(countyCustomAdapter);
        County_Pics.recycle();
        CountylistView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        final String county = ListrowItems.get(position).getCounty_Name();
        Intent intent = new Intent(this, CountyHospital.class);
        Bundle bundleCounty=new Bundle();
        bundleCounty.putString("COUNTY",county);
        intent.putExtras(bundleCounty);
        startActivity(intent);

    }

}
