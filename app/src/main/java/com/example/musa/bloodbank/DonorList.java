package com.example.musa.bloodbank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonorList extends Fragment {


    ListView donorList;
    Button btnAllDonor;

    private List<Donor> donors = new ArrayList<>();
    private List<Donor> allDoners = new ArrayList<>();

    SearchAdapter searchAdapter;
    DonnerDataSource dataSource;

    public DonorList() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_donor_list, container, false);

        donorList = (ListView) v.findViewById(R.id.donorList);
        btnAllDonor = (Button) v.findViewById(R.id.btnAllDonor);
        dataSource = new DonnerDataSource(this.getContext());

        searchAdapter = new SearchAdapter(this.getContext(), R.layout.search_row);
        donorList.setAdapter(searchAdapter);

        btnAllDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchAdapter.list.clear();
                searchAdapter.notifyDataSetChanged();

                donors = dataSource.getAllDonor();
                for (Donor d : donors) {
                    int id = d.getDonorID();
                    String name = d.getDonorName();
                    String mail = d.getDonorEmail();
                    String contact = d.getDonorContact();
                    String age = d.getDonorAge();
                    String city = d.getDonorCity();
                    String group = d.getDonorGroup();

                    Donor donor = new Donor(id, name, mail, contact, age, city, group);
                    Donor.addDonor(donor);
                    searchAdapter.add(donor);

                }
            }
        });

        allDoners = Donor.getDonors();

        donorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        View vw = inflater.inflate(R.layout.details, null);
        builder.setView(vw);

        final TextView id = vw.findViewById(R.id.showId);
        TextView name = vw.findViewById(R.id.showName);
        TextView contact = vw.findViewById(R.id.showPhone);
        TextView mail = vw.findViewById(R.id.showMail);
        TextView dob = vw.findViewById(R.id.showDOB);
        TextView city = vw.findViewById(R.id.showCity);
        TextView blood = vw.findViewById(R.id.showBloodGroup);

        Button delete = vw.findViewById(R.id.delete);

        id.setText(String.valueOf(donor.getDonorID()));
        name.setText(donor.getDonorName());
        contact.setText(donor.getDonorContact());
        mail.setText(donor.getDonorEmail());
        dob.setText(donor.getDonorAge());
        city.setText(donor.getDonorCity());
        blood.setText(donor.getDonorGroup());

        final AlertDialog alertDialog = builder.create();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                int u_id = Integer.parseInt(id.getText().toString());
                
                boolean isDeleted = dataSource.deleteDonner(u_id);
                
                if(isDeleted){
                    Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Not Deleted", Toast.LENGTH_SHORT).show();
                }
                alertDialog.cancel();
            }
        });

        alertDialog.show();


    }
}