package com.company.transport.driverterminal.ui.main.parcels.di.modules;

import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ParcelsModule {

    @Provides
    @FragmentScope
    ParcelsContract.Model provideParcelsModel(TransportCompanyApiClient apiClient) {
        return new ParcelsModel(apiClient);
    }
}
