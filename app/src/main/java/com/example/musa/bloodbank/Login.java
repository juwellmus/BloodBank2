package com.example.musa.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    Button btnSignUp, btnLogin;
    EditText eUserName,ePassword;
    String user;
    String userName;
    String password;
    Context context;

    private DonnerDataSource donnerDataSource;
    private List<Donor> donners = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        donnerDataSource = new DonnerDataSource(this);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        //btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin = (Button) findViewById(R.id.btnGetStart);

        eUserName = (EditText) findViewById(R.id.UserName);
        ePassword = (EditText) findViewById(R.id.Password);


        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     user = eUserName.getText().toString();
                    String pass = ePassword.getText().toString();

                    String password = donnerDataSource.searchPassword(user);
                    if(pass.equals(password)){

                        Intent intent = new Intent(Login.this,MainActivity.class);
                        intent.putExtra("userName",user);
                        startActivity(intent);

                    }
                    else {
                        ePassword.setError("Don't Match");
                    }
                }
            });
            
            


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });
       /* btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Login.class);
                startActivity(intent);
            }
        });*/
    }

    public Bundle getMyData() {
        String userName = eUserName.getText().toString();
        Bundle hm = new Bundle();
        hm.putString("userName",userName);
        return hm;
    }

}
