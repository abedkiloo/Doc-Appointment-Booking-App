package com.example.abedx.docbook;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewHospital extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] County_Names;//hold county names from the string array
    TypedArray County_Pics;//hold the interger values of the images from the string
    String[] NumberOfHospitals;
    String[] MainSites;
    List<RowItem> rowItems;
    ListView CountylistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hospital);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        rowItems = new ArrayList<RowItem>();
        County_Names = getResources().getStringArray(R.array.Counties);
        County_Pics = getResources().obtainTypedArray(R.array.CountyPics);
        NumberOfHospitals = getResources().getStringArray(R.array.NumberOFHospital);
        MainSites = getResources().getStringArray(R.array.MainSites);
        for (int i = 0; i < County_Names.length; i++) {

            RowItem pickedItems = new RowItem(
                    County_Names[i], County_Pics.getResourceId(i, -1),
                    NumberOfHospitals[i], MainSites[i]);
            rowItems.add(pickedItems);
        }
        CountylistView = (ListView) findViewById(R.id.CountylistView);
        CountyCustomAdapter countyCustomAdapter = new CountyCustomAdapter(this, rowItems);
        CountylistView.setAdapter(countyCustomAdapter);
        County_Pics.recycle();
        CountylistView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String member_name = rowItems.get(position).getCounty_Name();
        Toast.makeText(getApplicationContext(), "" + member_name,
                Toast.LENGTH_SHORT).show();
    }

}
