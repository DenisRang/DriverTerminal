package com.company.transport.driverterminal.di.modules;

import com.company.transport.driverterminal.di.ActivityScope;
import com.company.transport.driverterminal.ui.authorization.AuthorizationContract;
import com.company.transport.driverterminal.ui.authorization.AuthorizationPresenter;
import com.company.transport.driverterminal.ui.authorization.loading.LoadingContract;
import com.company.transport.driverterminal.ui.authorization.loading.LoadingPresenter;
import com.company.transport.driverterminal.ui.main.MainContract;
import com.company.transport.driverterminal.ui.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PresenterBindingModule {

    @Binds
    @ActivityScope
    public abstract AuthorizationContract.Presenter bindAuthorizationPresenter(AuthorizationPresenter presenter);

    @Binds
    @ActivityScope
    public abstract LoadingContract.Presenter bindLoadingPresenter(LoadingPresenter presenter);

    @Binds
    @ActivityScope
    public abstract MainContract.Presenter bindMainPresenter(MainPresenter presenter);

}
