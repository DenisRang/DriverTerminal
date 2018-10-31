package com.company.transport.driverterminal.ui.main.parcels.di.components;

import com.company.transport.driverterminal.di.AppScope;
import com.company.transport.driverterminal.di.modules.ApplicationModule;
import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.ui.main.parcels.di.modules.ContextModule;
import com.company.transport.driverterminal.utils.NetworkManager;

import dagger.Component;

@AppScope
@Component(modules = {ApplicationModule.class,ContextModule.class})
public interface ApplicationComponentForParcels {

    NetworkManager networkManager();

    TransportCompanyApiClient transportCompanyApiClient();
}
