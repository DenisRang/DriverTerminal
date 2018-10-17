package com.company.transport.driverterminal.ui.base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view, boolean isNew);

    void detachView();

    void stop();
}
