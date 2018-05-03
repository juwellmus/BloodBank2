package com.example.musa.bloodbank;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Personal_Details extends Fragment {

    Button btnDetails,btnEdit;
    TextView tvId,tvName,tvEmail,tvContact,tvAddress,tvCity,tvDoB,tvUser,tvPass;
    DonnerDataSource dataSource;

   /* int u_id;
    String u_name;
    String u_mail;
    String u_contact;
    String u_city;
    String u_user;
    String u_pass;*/

    List<Donor> donors = new ArrayList<>();
    String userName="";


    public Personal_Details() {

    }
   public static Personal_Details newInstance(String s) {
        Personal_Details result = new Personal_Details();
        Bundle bundle = new Bundle();
        bundle.putString("UserName", s);
        result.setArguments(bundle);
        return result;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_details, container, false);


        btnDetails = (Button) v.findViewById(R.id.btnDetails);
        btnEdit = (Button) v.findViewById(R.id.edit);

        tvId = (TextView) v.findViewById(R.id.id);
        tvName = (TextView) v.findViewById(R.id.name);
        tvEmail = (TextView) v.findViewById(R.id.email);
        tvContact = (TextView) v.findViewById(R.id.contactNo);
        tvCity = (TextView) v.findViewById(R.id.city);
        tvDoB = (TextView) v.findViewById(R.id.dob);
        tvUser = (TextView) v.findViewById(R.id.userName);
        tvPass = (TextView) v.findViewById(R.id.password);
        dataSource = new DonnerDataSource(this.getContext());


        userName = getArguments().getString("UserName");


        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               donors = dataSource.personalDetails(userName);
               for (Donor d:donors){
                   tvId.setText(String.valueOf(d.getDonorID()));
                   tvName.setText(d.getDonorName());
                   tvEmail.setText(d.getDonorEmail());
                   tvContact.setText(d.getDonorContact());
                   tvCity.setText(d.getDonorCity());
                   tvDoB.setText(d.getDonorAge());
                   tvUser.setText(d.getDonorUserName());
                   tvPass.setText(d.getDonorUserPass());

               }



            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDailog();
            }
        });

        return v;
    }

   /* private void showAlertDailog(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View vw = inflater.inflate(R.layout.update,null);

        TextView id = vw.findViewById(R.id.id);
        EditText name = vw.findViewById(R.id.name);
        EditText contact = vw.findViewById(R.id.contactNo);
        EditText mail = vw.findViewById(R.id.email);
        EditText city = vw.findViewById(R.id.city);
        final EditText userName = vw.findViewById(R.id.userName);
        EditText pass = vw.findViewById(R.id.password);

        Button update = vw.findViewById(R.id.update);
        builder.setView(vw);

        bLoginOk = vw.findViewById(R.id.btnLogin);
        bCancel = vw.findViewById(R.id.btnCancel);
        final AlertDialog alertDialog = builder.create();

        bLoginOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = ePass.getText().toString();
                if (!user.isEmpty() && !pass.isEmpty()) {
                    if (user.equals(USER) && pass.equals(PASS)) {
                        Toast.makeText(Employee.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                        //isLogedIn= true;
                        userPreference.setStatus(true);
                        alertDialog.cancel();

                    } else {
                        eUser.setError("User Name/Password Wrong !!");
                        ePass.setError("User Name/Password Wrong !!");
                    }
                }else {
                    eUser.setError("Fill this Field");
                    ePass.setError("Fill this Field");
                }
            }
        });
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        alertDialog.setCancelable(false);
        // builder.show();
        alertDialog.show();



    }
    */

    public void showAlertDailog() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View vw = inflater.inflate(R.layout.update, null);
        builder.setView(vw);


        final TextView id = vw.findViewById(R.id.id);
        final EditText name = vw.findViewById(R.id.name);
        final EditText contact = vw.findViewById(R.id.contactNo);
        final EditText mail = vw.findViewById(R.id.email);
        final EditText city = vw.findViewById(R.id.city);
        final EditText user = vw.findViewById(R.id.userName);
        final EditText pass = vw.findViewById(R.id.password);

        Button update = vw.findViewById(R.id.update);


        donors = dataSource.personalDetails(userName);
        for (Donor d:donors){
            id.setText(String.valueOf(d.getDonorID()));
            name.setText(d.getDonorName());
            mail.setText(d.getDonorEmail());
            contact.setText(d.getDonorContact());
            city.setText(d.getDonorCity());
            user.setText(d.getDonorUserName());
            pass.setText(d.getDonorUserPass());

        }


        final AlertDialog alertDialog = builder.create();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int u_id = Integer.parseInt(id.getText().toString());
                String u_name = name.getText().toString();
                String u_mail = mail.getText().toString();
                String u_contact = contact.getText().toString();
                String u_city = city.getText().toString();
                String u_user = user.getText().toString();
                String u_pass = pass.getText().toString();

                boolean isUpdated = dataSource.updateDonner(u_id,u_name,u_mail,u_contact,u_city,u_user,u_pass);

                if(isUpdated){
                    Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Data not Updated", Toast.LENGTH_SHORT).show();
                }
                alertDialog.cancel();

            }
        });

        alertDialog.show();
    }

}
