package com.example.musa.bloodbank;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBlood extends Fragment {

    private Spinner spinner;
    private ListView searchList;
    TextView countDonor;
    Button bSearch;
    String contact;



    private List<Donor> donors = new ArrayList<>();

    SearchAdapter searchAdapter;

    private DonnerDataSource dataSource;
    private String bloodGroup = "";
    String[] blood = {"Select...",
            "A-",
            "A+",
            "AB+",
            "AB-",
            "B+",
            "B-",
            "O+",
            "O-"
    };

    public SearchBlood() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_blood, container, false);

        spinner = v.findViewById(R.id.bloodGroup);
        bSearch = (Button) v.findViewById(R.id.search);
        searchList = (ListView) v.findViewById(R.id.searchList);
        countDonor = (TextView) v.findViewById(R.id.countDonor);

        dataSource = new DonnerDataSource(this.getContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.support_simple_spinner_dropdown_item,blood);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodGroup = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchAdapter = new SearchAdapter(this.getContext(),R.layout.search_row);
        searchList.setAdapter(searchAdapter);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAdapter.list.clear();
                searchAdapter.notifyDataSetChanged();

                int count=0;
                donors = dataSource.searchBlood(bloodGroup);
                for (Donor d: donors){
                    String name = d.getDonorName();
                    contact = d.getDonorContact();
                    String city = d.getDonorCity();

                    Donor donor = new Donor(name,contact,city);
                    searchAdapter.add(donor);
                    count++;

                }
                countDonor.setText("Total Donor : "+String.valueOf(count)+"");
            }
        });

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Donor donor = donors.get(position);
                showAlertDailog(donor);
            }
        });


        return v;
    }
    private void showAlertDailog(Donor donor) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View vw = inflater.inflate(R.layout.call_donor, null);
        builder.setView(vw);

        final TextView contact = vw.findViewById(R.id.showPhone);
        ImageButton btnCall = vw.findViewById(R.id.btnCall);
        contact.setText(donor.getDonorContact());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  phoneNumber = contact.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
            }
        });


        builder.show();
    }

}
