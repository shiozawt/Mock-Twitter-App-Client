package edu.byu.cs.tweeter.view;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabLayoutViewPagerAdapter extends FragmentPagerAdapter {

    private final int numTabs;
    public TabLayoutViewPagerAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new signinFragment();
            case 1:
                return new registerFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
