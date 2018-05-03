package com.example.musa.bloodbank;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Musa on 4/29/2018.
 */

public class SearchAdapter extends ArrayAdapter {

    List list= new ArrayList();
    private Context context;
    List<Donor> donors;

    public SearchAdapter(Context context, int resource){
        super(context,resource);
    }
    public SearchAdapter(Context context, List<Donor> donors){
        super(context,R.layout.search_row,donors);
        this.context=context;
        this.donors=donors;
    }
    static class LayoutHandler{
        TextView NAME,CONTACT,CITY;
    }
    public void add(Object object){
        super.add(object);
        list.add(object);

    }


    public int getCount(){
        return list.size();
    }
    public Object getTtem(int position){
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.search_row,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.NAME=(TextView)row.findViewById(R.id.name);
            layoutHandler.CONTACT=(TextView)row.findViewById(R.id.phone);
            layoutHandler.CITY=(TextView)row.findViewById(R.id.city);
            row.setTag(layoutHandler);
        }

        else{
            layoutHandler=(LayoutHandler)row.getTag();
        }
        Donor donor=(Donor) this.getTtem(position);
        layoutHandler.NAME.setText(donor.getDonorName());
        layoutHandler.CONTACT.setText(donor.getDonorContact());
        layoutHandler.CITY.setText(donor.getDonorCity());

        return row;
    }
}