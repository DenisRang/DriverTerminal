package com.company.transport.driverterminal.ui.main;


import com.company.transport.driverterminal.ui.base.BaseModel;
import com.company.transport.driverterminal.ui.base.BasePresenter;
import com.company.transport.driverterminal.ui.base.BaseView;

public interface MainContract {

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {

        int getCurrentPagerItem();

        void setCurrentPagerItem(int currentPagerItem);

    }

    interface View extends BaseView {

    }

}
