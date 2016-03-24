package com.example.abedx.docbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abedx on 3/24/2016.
 */
public class PharmacyList extends BaseAdapter {
    private List<GetterSetterPharmacy> listItems;
    private Context classContext;
    public PharmacyList(Context c_Context,List<GetterSetterPharmacy> pharmacyList)
    {
        this.listItems =pharmacyList;
        this.classContext=c_Context;
    }
    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ElementList elementList=new ElementList();
        LayoutInflater layoutInflater=(LayoutInflater)classContext.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {

            convertView=layoutInflater.inflate(R.layout.pharmacylist,null);
            elementList.pharmacyCounty =(TextView)convertView.findViewById(R.id.textViewPharmacyCountyName);
            elementList.pharmacyName =(TextView)convertView.findViewById(R.id.textViewPharmacyName);
            convertView.setTag(elementList);

        }
        else
        {
            elementList=(ElementList)convertView.getTag();
        }
        GetterSetterPharmacy hospitalNames=listItems.get(position);
        elementList.pharmacyCounty.setText(hospitalNames.getPharmacyID());
        elementList.pharmacyName.setText(hospitalNames.getPharmacyName());
        return convertView;
    }

    public  class ElementList
    {
        TextView pharmacyCounty;
        TextView pharmacyName;
    }

}
