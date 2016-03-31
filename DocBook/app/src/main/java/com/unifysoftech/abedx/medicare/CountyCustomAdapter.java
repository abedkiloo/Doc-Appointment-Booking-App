package com.unifysoftech.abedx.medicare;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abedx on 2/22/2016.
 */
public class CountyCustomAdapter extends BaseAdapter {
    Context classcontext;
    List<RowItem> county_Items;

    CountyCustomAdapter(Context context, List<RowItem> countyItems) {
        this.classcontext = context;
        this.county_Items = countyItems;
    }

    @Override
    public int getCount() {
        return county_Items.size();
    }

    @Override
    public Object getItem(int position) {
        return county_Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return county_Items.indexOf(getItem(position));
    }

    /*
        Custom view Holder Class
        */
    private class ViewHolder {
        ImageView countyPic;
        TextView NumberOfHospital;
        TextView MainSites;
        TextView HosptalName;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater layoutInflater = (LayoutInflater) classcontext.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE);
        viewHolder = new ViewHolder();
        if (convertView == null) {
            //inflater with the custom layout that was created containing the custom view
            convertView = layoutInflater.inflate(R.layout.county_lists, null);
            //get reference of the members of the custom list to be made and set the to the private class view holder values
            viewHolder.HosptalName = (TextView) convertView.findViewById(R.id.member_name);
            viewHolder.countyPic = (ImageView) convertView.findViewById(R.id.profile_pic);
            viewHolder.NumberOfHospital = (TextView) convertView.findViewById(R.id.contact_type);
            viewHolder.MainSites = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        RowItem rowItem = county_Items.get(position);
        viewHolder.HosptalName.setText(rowItem.getCounty_Name());
        viewHolder.countyPic.setImageResource(rowItem.getCounty_Pics());
        viewHolder.NumberOfHospital.setText(rowItem.getC_Status());
        viewHolder.MainSites.setText(rowItem.getMain_Sites());
        return convertView;
    }

}
