package com.company.transport.driverterminal.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.company.transport.driverterminal.TerminalApplication;
import com.company.transport.driverterminal.utils.PresenterCache;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public abstract class BaseActivityView<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T presenter;

    private PresenterCache presenterCache;
    private boolean isRestoredPresenter;

    @Override
    public void onStop() {
        super.onStop();
        if (!isChangingConfigurations()) {
            // activity is stopped normally, remove the cached presenter so it's not cached
            // even if activity gets killed
            onStopNormally();
            presenterCache.removePresenter(presenter);
            presenter.stop();
        } else {
            onStopWhenRotating();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    protected void attachConfiguredViewToPresenter() {
        presenterCache = TerminalApplication.getPresenterCache();
        restoreOrCreatePresenter();
        presenter.attachView(this, !isRestoredPresenter);
    }

    protected void onStopNormally() {

    }

    protected void onStopWhenRotating() {

    }

    private void restoreOrCreatePresenter() {
        isRestoredPresenter = true;
        // try to get a cached presenterd
        presenter = presenterCache.getPresenter(getClass().getName());
        if (presenter == null) {
            // no cached one found, create a new one
            isRestoredPresenter = false;
            AndroidInjection.inject(this);
            presenterCache.putPresenter(getClass().getName(), presenter);
        }
    }
}
