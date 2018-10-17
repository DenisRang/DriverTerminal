package com.company.transport.driverterminal.ui.authorization.loading;


import com.company.transport.driverterminal.ui.base.BasePresenter;
import com.company.transport.driverterminal.ui.base.BaseView;

public interface LoadingContract {

    interface View extends BaseView {
        void navigateToMainActivity();

        void navigateToAuthActivity();
    }

    interface Presenter extends BasePresenter<View> {
        void autoLogin();
    }

}
