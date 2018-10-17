package com.company.transport.driverterminal.ui.authorization;

import com.company.transport.driverterminal.ui.base.BasePresenter;
import com.company.transport.driverterminal.ui.base.BaseView;


public interface AuthorizationContract {
    interface View extends BaseView {
        String getLogin();

        String getPassword();

        void resetPassword();

        void showLoadingDialog();

        void dismissLoadingDialog();

        void showLoginError();

        void showWrongEmail();

        void showPasswordError();

        void showPoorConnection();

        void showNoConnection();

        void navigateToMainActivity();

    }

    interface Presenter extends BasePresenter<View> {
        void login();
    }
}
