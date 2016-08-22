package com.unifysoftech.abedxh.docbook.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unifysoftech.abedx.medicare.R;

/**
 * Created by abedx on 4/5/2016.
 */
public class MostVisitedDoctors extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mostvisiteddoctors,container,false);
    }
}
