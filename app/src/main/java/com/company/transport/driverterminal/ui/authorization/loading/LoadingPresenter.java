package com.company.transport.driverterminal.ui.authorization.loading;

import android.support.annotation.Nullable;

import com.company.transport.driverterminal.utils.AuthorizationManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoadingPresenter implements LoadingContract.Presenter {

    private final AuthorizationManager authorizationManager;
    @Nullable
    private LoadingContract.View view;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    LoadingPresenter(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Override
    public void attachView(LoadingContract.View view, boolean isNew) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void stop() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void autoLogin() {
        if (view == null) return;
        if (authorizationManager.getAuthToken() == null) {
            view.navigateToAuthActivity();
            return;
        } else {
            view.navigateToMainActivity();
        }
    }
}
