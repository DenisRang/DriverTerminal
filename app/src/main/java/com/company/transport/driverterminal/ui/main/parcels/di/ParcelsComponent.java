package com.company.transport.driverterminal.ui.main.parcels.di;

import com.company.transport.driverterminal.di.CompletedParcelsFragmentScope;
import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.di.qualifiers.CompletedParcelsPresenter;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.view.ParcelsFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {ParcelsModule.class,ApplicationModuleForParcels.class},dependencies = ModelComponent.class)
public interface ParcelsComponent {

    void inject(ParcelsFragment parcelsFragment);

}

