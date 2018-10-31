package com.company.transport.driverterminal.ui.main.parcels.di.modules;

import com.company.transport.driverterminal.ui.main.parcels.di.qualifiers.CompletedParcelsPresenter;
import com.company.transport.driverterminal.ui.main.parcels.di.qualifiers.IncomingParcelsPresenter;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsPresenter;
import com.company.transport.driverterminal.ui.main.parcels.di.scopes.CompletedParcelsFragmentScope;
import com.company.transport.driverterminal.ui.main.parcels.di.scopes.IncomingParcelsFragmentScope;
import com.company.transport.driverterminal.utils.NetworkManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DifferentParcelsModule {

    @IncomingParcelsFragmentScope
    @IncomingParcelsPresenter
    @Provides
    public ParcelsContract.Presenter incomingParcelsPresenter(NetworkManager networkManager,
                                                              ParcelsContract.Model model) {
        return new ParcelsPresenter(networkManager, model);
    }

    @CompletedParcelsFragmentScope
    @CompletedParcelsPresenter
    @Provides
    public ParcelsContract.Presenter completedParcelsPresenter(NetworkManager networkManager,
                                                      ParcelsContract.Model model) {
        return new ParcelsPresenter(networkManager, model);
    }

}
