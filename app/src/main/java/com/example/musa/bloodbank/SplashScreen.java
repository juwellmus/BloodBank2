package com.example.musa.bloodbank;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(SplashScreen.this, Login.class);
                startActivity(homeIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
