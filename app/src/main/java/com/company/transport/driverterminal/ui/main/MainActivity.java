package com.company.transport.driverterminal.ui.main;

import android.os.Bundle;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.ui.base.BaseActivityView;

public class MainActivity extends BaseActivityView<MainPresenter> implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachConfiguredViewToPresenter();
    }
}
