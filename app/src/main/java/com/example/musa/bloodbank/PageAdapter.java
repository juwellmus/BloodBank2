package com.example.musa.bloodbank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Musa on 4/29/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    int mNoOfTab;
    String userName;

    public PageAdapter(FragmentManager fm, int mNoOfTab,String userName) {
        super(fm);
        this.mNoOfTab = mNoOfTab;
        this.userName = userName;
    }


    @Override
    public Fragment getItem(int position) {


        switch(position)
        {
            case 0:
                SearchBlood sb = new SearchBlood();
                return sb;
            case 1:
               /* Personal_Details bb = new Personal_Details();
                return bb;*/
                return Personal_Details.newInstance(userName);
            case 2:
                DonorList pd = new DonorList();
                return pd;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return mNoOfTab;
    }
}
