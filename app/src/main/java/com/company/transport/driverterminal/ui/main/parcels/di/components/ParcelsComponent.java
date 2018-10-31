package com.company.transport.driverterminal.ui.main.parcels.di.components;

import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.di.modules.ParcelsModule;
import com.company.transport.driverterminal.utils.NetworkManager;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponentForParcels.class,modules = ParcelsModule.class)
public interface ParcelsComponent {

    ParcelsContract.Model parcelsModel();

    NetworkManager networkManager();

}

