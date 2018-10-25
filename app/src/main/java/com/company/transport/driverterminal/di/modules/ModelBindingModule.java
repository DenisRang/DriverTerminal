package com.company.transport.driverterminal.di.modules;

import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsModel;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ModelBindingModule {

    @Binds
    @FragmentScope
    public abstract ParcelsContract.Model bindParcelsModel(ParcelsModel model);
}
