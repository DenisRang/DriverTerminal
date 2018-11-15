package com.company.transport.driverterminal.ui.authorization;

import android.support.annotation.Nullable;

import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.utils.AuthorizationManager;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import timber.log.Timber;

public class AuthorizationPresenter implements AuthorizationContract.Presenter {

    private final AuthorizationManager authorizationManager;
    private final TransportCompanyApiClient client;

    @Nullable
    private AuthorizationContract.View view;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    AuthorizationPresenter(AuthorizationManager authorizationManager, TransportCompanyApiClient client) {
        this.authorizationManager = authorizationManager;
        this.client = client;
    }

    @Override
    public void attachView(AuthorizationContract.View view, boolean isNew) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void stop() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void login() {
        if (view == null) return;
        String login = view.getLogin();
        String password = view.getPassword();
        if (login.isEmpty()) {
            view.showLoginError();
            view.resetPassword();
            return;
        }
        if (password.isEmpty()) {
            view.showPasswordError();
            return;
        }
        view.showLoadingDialog();
        makeLoginRequest(login, password);
    }

    private void makeLoginRequest(String login, String password) {
        disposable.add(client.authorize(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authResponse -> {
                            String authToken = authResponse.getAccessToken();
                            String errorMsg = authResponse.getMessage();
                            if (authToken != null) {
                                authorizationManager.saveResponseAuthToken(authToken);
                                if (view != null) {
                                    view.dismissLoadingDialog();
                                    view.navigateToMainActivity();
                                }
                            } else {
                                if (errorMsg != null && view != null) {
                                    view.showError(errorMsg);
                                }
                            }
                        }, error -> {
                            if (view != null) {
                                view.dismissLoadingDialog();
                                view.resetPassword();
                                //Проверка на ошибку от сервера
                                if (error instanceof NullPointerException) {
                                    view.showWrongEmail();
                                } else if (error instanceof HttpException) {
                                    switch (((HttpException) error).code()) {
                                        case 400:
                                            view.showWrongEmail();
                                            break;
                                    }
                                } else if (error instanceof SocketTimeoutException) {
                                    view.showPoorConnection();
                                } else if (error instanceof UnknownHostException || error instanceof ConnectException) {
                                    view.showNoConnection();
                                } else {
                                    Timber.e(error);
                                }
                            }
                        }
                ));
    }

}
