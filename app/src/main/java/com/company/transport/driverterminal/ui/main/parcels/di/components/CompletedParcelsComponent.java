package com.company.transport.driverterminal.ui.main.parcels.di.components;

import com.company.transport.driverterminal.ui.main.parcels.di.qualifiers.CompletedParcelsPresenter;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.di.modules.DifferentParcelsModule;
import com.company.transport.driverterminal.ui.main.parcels.di.scopes.CompletedParcelsFragmentScope;

import dagger.Component;

@CompletedParcelsFragmentScope
@Component(dependencies = ParcelsComponent.class, modules = DifferentParcelsModule.class)
public interface CompletedParcelsComponent {

    @CompletedParcelsPresenter
    ParcelsContract.Presenter getCompletedParcelPresenter();

}

