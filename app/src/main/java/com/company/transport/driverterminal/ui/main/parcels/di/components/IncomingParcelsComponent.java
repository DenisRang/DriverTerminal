package com.company.transport.driverterminal.ui.main.parcels.di.components;

import com.company.transport.driverterminal.ui.main.parcels.di.qualifiers.IncomingParcelsPresenter;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.di.modules.DifferentParcelsModule;
import com.company.transport.driverterminal.ui.main.parcels.di.scopes.IncomingParcelsFragmentScope;

import dagger.Component;

@IncomingParcelsFragmentScope
@Component(dependencies = ParcelsComponent.class, modules = DifferentParcelsModule.class)
public interface IncomingParcelsComponent {

    @IncomingParcelsPresenter
    ParcelsContract.Presenter getIncomingParcelPresenter();

}

