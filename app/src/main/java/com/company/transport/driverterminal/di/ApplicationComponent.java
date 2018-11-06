package com.company.transport.driverterminal.di;

import com.company.transport.driverterminal.TerminalApplication;
import com.company.transport.driverterminal.di.modules.ActivityFragmentBindingModule;
import com.company.transport.driverterminal.di.modules.ApplicationModule;
import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.utils.NetworkManager;
import com.company.transport.driverterminal.utils.PresenterCache;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@AppScope
@Component(modules = {ApplicationModule.class,
        ApplicationModule.Declarations.class,
        ActivityFragmentBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface ApplicationComponent extends AndroidInjector<TerminalApplication> {
    /**
     * Дает синтаксический сахар. Теперь можно с помощью
     * DaggerApplicationComponent.builder().application(this).build().inject(this);
     * никогда не создавать модули явно или указывать в какой модуль мы передаем application.
     * Application уже будет содержаться в нашем графе зависимостей.
     */
    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(TerminalApplication application);

        ApplicationComponent build();
    }

    PresenterCache getPresenterCache();

    NetworkManager networkManager();

    TransportCompanyApiClient client();


}
