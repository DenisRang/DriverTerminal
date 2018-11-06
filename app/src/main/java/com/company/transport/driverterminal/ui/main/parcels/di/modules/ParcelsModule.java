package com.company.transport.driverterminal.ui.main.parcels.di.modules;

import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsModel;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsPresenter;
import com.company.transport.driverterminal.utils.NetworkManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ParcelsModule {

    @FragmentScope
    @Provides
    public ParcelsContract.Presenter parcelsPresenter(NetworkManager networkManager,
                                                      ParcelsContract.Model model) {
        return new ParcelsPresenter(networkManager, model);
    }

    @FragmentScope
    @Provides
    ParcelsContract.Model provideParcelsModel(TransportCompanyApiClient apiClient) {
        return new ParcelsModel(apiClient);
    }
}
