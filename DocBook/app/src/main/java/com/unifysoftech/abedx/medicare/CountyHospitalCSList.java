package com.unifysoftech.abedx.medicare;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abedx on 3/20/2016.
 */
public class CountyHospitalCSList extends BaseAdapter {
    private List<HospitalNames> listItems;
    private Context classContext;
    public CountyHospitalCSList(Context c_Context,List<HospitalNames> hospital_Names)
    {
        this.listItems =hospital_Names;
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

            convertView=layoutInflater.inflate(R.layout.countyhospitalcatergory,null);
            elementList.hospitalID=(TextView)convertView.findViewById(R.id.TVhospitalD);
            elementList.CHospitalName=(TextView)convertView.findViewById(R.id.TVCatergoryHospitalName);
            convertView.setTag(elementList);

        }
        else
        {
            elementList=(ElementList)convertView.getTag();
        }
        HospitalNames hospitalNames=listItems.get(position);
      elementList.hospitalID.setText(hospitalNames.getHospitalID());
        elementList.CHospitalName.setText(hospitalNames.getHosptalName());
        return convertView;
    }

   public  class ElementList
    {
        TextView hospitalID;
        TextView CHospitalName;
    }

}
