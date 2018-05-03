package com.example.musa.bloodbank;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Registration extends AppCompatActivity {

    Button btnLogin,btnRegister;
    EditText eName,eEmail,eContact,eCity,eDoB,eUser,ePass;
    Spinner spinner;
    CheckBox checkBox;

    String bloodGroup="";

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

    private DonnerDataSource donnerDataSource;
    private Donor donner;
    int mYear,mMonth,mDay;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegister = (Button) findViewById(R.id.register);
        btnLogin = (Button) findViewById(R.id.login);

        spinner = (Spinner) findViewById(R.id.bloodGroup);

        donnerDataSource = new DonnerDataSource(this);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        eName = (EditText) findViewById(R.id.name);
        eEmail = (EditText) findViewById(R.id.email);
        eContact = (EditText) findViewById(R.id.contactNo);
        eCity = (EditText) findViewById(R.id.city);
        eDoB = (EditText) findViewById(R.id.dob);
        eUser = (EditText) findViewById(R.id.userName);
        ePass = (EditText) findViewById(R.id.password);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Registration.this,R.layout.support_simple_spinner_dropdown_item,blood);
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,Login.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = eName.getText().toString();
                String mail = eEmail.getText().toString();
                String contact = eContact.getText().toString();
                String city = eCity.getText().toString();
                String dob = eDoB.getText().toString();
                String user = eUser.getText().toString();
                String pass = ePass.getText().toString();

                donner = new Donor(name,mail,contact,dob,city,user,pass,bloodGroup);
                boolean status = donnerDataSource.insertDonner(donner);
                if(status){
                    Toast.makeText(Registration.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this,Login.class));
                }else{
                    Toast.makeText(Registration.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void selectDate(View view) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Registration.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                eDoB.setText(dayOfMonth+"/"+month+"/"+year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }
}
