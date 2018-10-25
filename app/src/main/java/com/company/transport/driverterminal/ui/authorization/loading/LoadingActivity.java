package com.company.transport.driverterminal.ui.authorization.loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.ui.authorization.AuthorizationActivity;
import com.company.transport.driverterminal.ui.main.view.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LoadingActivity extends AppCompatActivity implements LoadingContract.View {

    @Inject
    LoadingContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        AndroidInjection.inject(this);
        presenter.attachView(this, true);
        presenter.autoLogin();
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void navigateToAuthActivity() {
        Intent intent = new Intent(this, AuthorizationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
