package com.company.transport.driverterminal.ui.main.parcels.di;

import android.content.Context;

import com.company.transport.driverterminal.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @FragmentScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}