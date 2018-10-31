package com.company.transport.driverterminal.ui.main.parcels.di;

import com.company.transport.driverterminal.di.CompletedParcelsFragmentScope;
import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.di.IncomingParcelsFragmentScope;
import com.company.transport.driverterminal.di.modules.ApplicationModule;
import com.company.transport.driverterminal.di.qualifiers.CompletedParcelsPresenter;
import com.company.transport.driverterminal.di.qualifiers.IncomingParcelsPresenter;
import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsModel;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsPresenter;
import com.company.transport.driverterminal.utils.NetworkManager;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModuleForParcels.class)
public class ParcelsModule {

    @FragmentScope
    @Provides
    public ParcelsContract.Presenter parcelsPresenter(NetworkManager networkManager,
                                                              ParcelsContract.Model model) {
        return new ParcelsPresenter(networkManager, model);
    }

}
