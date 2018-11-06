package com.company.transport.driverterminal.ui.main.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.company.transport.driverterminal.di.qualifiers.IncomingParcelsPresenter;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsType;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsType;
import com.company.transport.driverterminal.ui.main.parcels.view.ParcelsFragment;

public class ParcelStatePagerAdapter extends FragmentStatePagerAdapter {
    public ParcelStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int i) {
        return (i == 0) ? ParcelsFragment.newInstance(ParcelsType.INCOMING) : ParcelsFragment.newInstance(ParcelsType.COMPLETED);
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return (i == 0) ? "Incoming" : "Completed";
    }
}
