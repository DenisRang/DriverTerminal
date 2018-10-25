package com.company.transport.driverterminal.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.ui.authorization.loading.LoadingActivity;
import com.company.transport.driverterminal.utils.AuthorizationManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class SettingsActivity extends AppCompatActivity {

    @Inject
    AuthorizationManager authorizationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        AndroidInjection.inject(this);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.button_logout)
    public void logout(View view) {
        authorizationManager.deleteToken();
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
    }
}
