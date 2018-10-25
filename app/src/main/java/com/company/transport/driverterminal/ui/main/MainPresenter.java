package com.company.transport.driverterminal.ui.main;

import android.support.annotation.Nullable;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {
    @Nullable
    protected MainContract.View view;

    private int currentPagerItem = -1;

    @Inject
    MainPresenter() {
        //required
    }

    @Override
    public void attachView(MainContract.View view, boolean isNew) {
        this.view = view;
        if (!isNew) {
            view.setCurrentPagerItem(currentPagerItem);
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void stop() {
    }

    @Override
    public void setCurrentPagerItem(int currentPagerItem) {
        this.currentPagerItem = currentPagerItem;
    }
}
