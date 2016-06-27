package com.winnipegapp.examples;

/**
 * Created by Mauricio on 2016-03-07.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence tabTitle[];
    int qtyTab;

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.tabTitle = mTitles;

        this.qtyTab = mNumbOfTabsumb;

    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {

            return (new RemovalServices());

        } else if (position == 1) {

            return (new UtilitiesServices());

        } else {

            return (new RoadTransitServices());

        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }

    @Override
    public int getCount() {

        return qtyTab;
    }


}
