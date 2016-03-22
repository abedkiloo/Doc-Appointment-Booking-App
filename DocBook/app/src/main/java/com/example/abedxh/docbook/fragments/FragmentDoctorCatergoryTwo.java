package com.example.abedxh.docbook.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abedx.docbook.R;

/**
 * Created by abedx on 3/21/2016.
 */
public class FragmentDoctorCatergoryTwo extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.doctorcatergorytow,container,false);
        return rootView;
    }
}
