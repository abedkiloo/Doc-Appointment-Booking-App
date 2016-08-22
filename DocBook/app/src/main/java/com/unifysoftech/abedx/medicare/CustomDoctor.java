package com.unifysoftech.abedx.medicare;

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
    List<GetterSetterDoctor> doctor_Items;

    public CustomDoctor(Context context, List<GetterSetterDoctor> countyItems) {
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
        TextView diseaseId;
        TextView doctSpcialation;

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
            viewHolder.doctorId = (TextView) convertView.findViewById(R.id.DOCTORID);
            viewHolder.doctorName = (TextView) convertView.findViewById(R.id.doctorName7);
            viewHolder.doctorHospital  = (TextView) convertView.findViewById(R.id.doctorHospitalName);
            viewHolder.diseaseId  = (TextView) convertView.findViewById(R.id.DISEASEID);
            viewHolder.doctSpcialation=(TextView)convertView.findViewById(R.id.customDoctorSpecilasation);
            // viewHolder.doctorOtherDetails = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
      GetterSetterDoctor getterSetterDoctor =doctor_Items.get(position);
        viewHolder.doctorName.setText(getterSetterDoctor.getDocName());
        viewHolder.doctorHospital.setText(getterSetterDoctor.getDocHospitals());
        viewHolder.doctorId.setText(getterSetterDoctor.getDoctorId());
        viewHolder.diseaseId.setText(getterSetterDoctor.getDISEASEID());
        viewHolder.doctSpcialation.setText(getterSetterDoctor.getDISEASESpecalisation());
        return convertView;
    }

}
