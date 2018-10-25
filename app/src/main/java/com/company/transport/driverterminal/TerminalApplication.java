package com.company.transport.driverterminal;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.company.transport.driverterminal.di.ApplicationComponent;
import com.company.transport.driverterminal.di.DaggerApplicationComponent;
import com.company.transport.driverterminal.utils.NotLoggingTree;
import com.company.transport.driverterminal.utils.PresenterCache;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

public class TerminalApplication extends DaggerApplication implements HasSupportFragmentInjector {

    private static ApplicationComponent appComponent;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new NotLoggingTree());
        }

        // Отключить вызов exceptions после отписки от observable
        RxJavaPlugins.setErrorHandler(throwable -> Timber.e(throwable.getMessage()));

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerApplicationComponent.builder().application(this).build();
        return appComponent;
    }

    public static PresenterCache getPresenterCache() {
        return appComponent.getPresenterCache();
    }
}
