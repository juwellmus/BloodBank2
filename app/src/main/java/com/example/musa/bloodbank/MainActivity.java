package com.example.musa.bloodbank;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Search Blood").setIcon(R.drawable.ic_action_search));
        tabLayout.addTab(tabLayout.newTab().setText("Personal Details").setIcon(R.drawable.blood));
        tabLayout.addTab(tabLayout.newTab().setText("Donor List").setIcon(R.drawable.ic_action_details));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);


        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("userName");

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final  PageAdapter adapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),userName);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);

        return true;
    }

   /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem logoutItem = menu.findItem(R.id.itemLogout);

        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemLogout:
               startActivity(new Intent(MainActivity.this,Login.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
