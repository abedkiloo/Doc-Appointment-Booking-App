package com.example.abedx.docbook;

/**
 * Created by abedx on 3/24/2016.
 */
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
public class CustomDoctor extends BaseAdapter {
    Context classcontext;
    List<GetySetterDoctor> doctor_Items;

    public CustomDoctor(Context context, List<GetySetterDoctor> countyItems) {
        this.classcontext = context;
        this.doctor_Items = countyItems;
    }

    @Override
    public int getCount() {
        return doctor_Items.size();
    }

    @Override
    public Object getItem(int position) {
        return doctor_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return doctor_Items.indexOf(getItem(position));
    }

    /*
        Custom view Holder Class
        */
    private class ViewHolder {
        TextView doctorId;
        TextView doctorName;
        TextView doctorOtherDetails;
        TextView doctorHospital;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = (LayoutInflater) classcontext.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE);
        viewHolder = new ViewHolder();
        if (convertView == null) {
            //inflater with the custom layout that was created containing the custom view
            convertView = layoutInflater.inflate(R.layout.doctorlist, null);
            //get reference of the members of the custom list to be made and set the to the private class view holder values
           // viewHolder.doctorId = (TextView) convertView.findViewById(R.id.member_name);
            viewHolder.doctorName = (TextView) convertView.findViewById(R.id.doctorName7);
            viewHolder.doctorHospital  = (TextView) convertView.findViewById(R.id.doctorHospitalName);
           // viewHolder.doctorOtherDetails = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
      GetySetterDoctor getySetterDoctor=doctor_Items.get(position);
        viewHolder.doctorName.setText(getySetterDoctor.getDocName());
        viewHolder.doctorHospital.setText(getySetterDoctor.getDocHospitals());
        return convertView;
    }

}
