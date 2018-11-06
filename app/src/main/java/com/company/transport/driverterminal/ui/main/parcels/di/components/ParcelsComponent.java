package com.company.transport.driverterminal.ui.main.parcels.di.components;

import com.company.transport.driverterminal.di.ApplicationComponent;
import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.di.modules.ParcelsModule;
import com.company.transport.driverterminal.ui.main.parcels.view.ParcelsFragment;
import com.company.transport.driverterminal.utils.NetworkManager;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = ParcelsModule.class)
public interface ParcelsComponent {

    ParcelsContract.Presenter getParcelPresenter();

    ParcelsContract.Model parcelsModel();

    void inject(ParcelsFragment parcelsFragment);

}

