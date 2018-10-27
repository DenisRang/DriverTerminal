package com.company.transport.driverterminal.di.modules;

import com.company.transport.driverterminal.di.ActivityScope;
import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.di.qualifiers.CompletedParcelsPresenter;
import com.company.transport.driverterminal.di.qualifiers.IncomingParcelsPresenter;
import com.company.transport.driverterminal.ui.authorization.AuthorizationContract;
import com.company.transport.driverterminal.ui.authorization.AuthorizationPresenter;
import com.company.transport.driverterminal.ui.authorization.loading.LoadingContract;
import com.company.transport.driverterminal.ui.authorization.loading.LoadingPresenter;
import com.company.transport.driverterminal.ui.main.MainContract;
import com.company.transport.driverterminal.ui.main.MainPresenter;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsPresenter;

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

    @Binds
    @FragmentScope
    public abstract ParcelsContract.Presenter bindParcelsPresenter(ParcelsPresenter presenter);
}
