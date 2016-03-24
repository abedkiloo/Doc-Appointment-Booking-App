package com.example.abedxh.docbook.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.abedx.docbook.R;

public class FoodPoisoning extends Fragment{

  //EditText edval,alu2;
   // Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_food_poisoning, container, false);
        EditText  edval=(EditText)rootView.findViewById(R.id.FoodPoisoning1);
        final EditText alu2=(EditText)rootView.findViewById(R.id.FoodPoisoning2);
       Button btn=(Button)rootView.findViewById(R.id.btnFood);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alu2.setText("Cleda workeds");
            }
        });
        return rootView;
    }


}
