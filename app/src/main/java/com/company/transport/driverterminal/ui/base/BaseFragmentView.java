package com.company.transport.driverterminal.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.company.transport.driverterminal.TerminalApplication;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.utils.NetworkManager;
import com.company.transport.driverterminal.utils.PresenterCache;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

public abstract class BaseFragmentView<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected T presenter;

    private PresenterCache presenterCache;
    private boolean isRestoredPresenter;

    @Override
    public void onAttach(Context context) {
        Timber.d("onAttach");
        presenterCache = TerminalApplication.getPresenterCache();
        restoreOrCreatePresenter();
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!getActivity().isChangingConfigurations()) {
            // fragment is stopped normally, remove the cached presenter so it's not cached
            // even if fragment gets killed
            onStopNormally();
            presenterCache.removePresenter(presenter);
            presenter.stop();
        } else {
            onStopWhenRotating();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    protected void attachConfiguredViewToPresenter() {
        presenter.attachView(this, !isRestoredPresenter);
    }

    protected void onStopNormally() {

    }

    protected void onStopWhenRotating() {

    }

    protected String getPresenterSavingName() {
        return getClass().getName();
    }

    protected void inject() {
        AndroidSupportInjection.inject(this);
    }

    private void restoreOrCreatePresenter() {
        isRestoredPresenter = true;
        // try to get a cached presenterd
        presenter = presenterCache.getPresenter(getPresenterSavingName());
        if (presenter == null) {
            // no cached one found, create a new one
            isRestoredPresenter = false;
            inject();
            presenterCache.putPresenter(getClass().getName(), presenter);
        }
    }
}
