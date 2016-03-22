package com.example.abedx.docbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abedx on 3/16/2016.
 */
public class HospitalsCustomList extends BaseAdapter {
    Context classContex;
    List<HospitalNames> LhospitalNames;
    public HospitalsCustomList(Context G_context, List<HospitalNames> G_list)
    {
        this.classContex=G_context;
        this.LhospitalNames=G_list;
    }
    @Override
    public int getCount() {
        return LhospitalNames.size();
    }

    @Override
    public Object getItem(int position) {
        return LhospitalNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return LhospitalNames.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView);
    }

    @NonNull
    private View getView(int position, View convertView) {
        HospitalTV hospitalTV=new HospitalTV();
        LayoutInflater layoutInflater=(LayoutInflater)classContex.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
        convertView=layoutInflater.inflate(R.layout.hospitallist,null);
            hospitalTV.HospitalTV=(TextView)convertView.findViewById(R.id.HOSPITALNAME);
            convertView.setTag(hospitalTV);
        }
        else
        {
            hospitalTV=(HospitalTV)convertView.getTag();
        }
        HospitalNames hospitalNames=LhospitalNames.get(position);
        hospitalTV.HospitalTV.setText(hospitalNames.getHosptalName());
        return convertView;
    }


    class HospitalTV
    {
        TextView HospitalTV;
    }

}
