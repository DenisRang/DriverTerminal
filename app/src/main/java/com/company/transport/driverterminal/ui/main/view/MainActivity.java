package com.company.transport.driverterminal.ui.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.ui.SettingsActivity;
import com.company.transport.driverterminal.ui.base.BaseActivityView;
import com.company.transport.driverterminal.ui.main.MainContract;
import com.company.transport.driverterminal.ui.main.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivityView<MainPresenter> implements MainContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_tabs)
    TabLayout tabsLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        attachConfiguredViewToPresenter();

        setSupportActionBar(toolbar);
        viewPager.setAdapter(new ParcelStatePagerAdapter(getSupportFragmentManager()));
        tabsLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onStopWhenRotating() {
        presenter.setCurrentPagerItem(viewPager.getCurrentItem());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setCurrentPagerItem(int currentPagerItem) {
        viewPager.setCurrentItem(currentPagerItem);
    }
}
