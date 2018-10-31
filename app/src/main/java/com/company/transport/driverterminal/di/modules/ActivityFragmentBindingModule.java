package com.company.transport.driverterminal.di.modules;

import com.company.transport.driverterminal.di.ActivityScope;
import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.ui.SettingsActivity;
import com.company.transport.driverterminal.ui.authorization.AuthorizationActivity;
import com.company.transport.driverterminal.ui.authorization.loading.LoadingActivity;
import com.company.transport.driverterminal.ui.main.parcels.view.ParcelsFragment;
import com.company.transport.driverterminal.ui.main.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityFragmentBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = PresenterBindingModule.class)
    abstract AuthorizationActivity authorizationActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = PresenterBindingModule.class)
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = PresenterBindingModule.class)
    abstract LoadingActivity loadingActivity();

    @ActivityScope
    @ContributesAndroidInjector()
    abstract SettingsActivity settingsActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = {PresenterBindingModule.class, ModelBindingModule.class})
    abstract ParcelsFragment parcelsFragment();

}
