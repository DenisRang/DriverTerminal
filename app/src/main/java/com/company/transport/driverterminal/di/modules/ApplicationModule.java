package com.company.transport.driverterminal.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.company.transport.driverterminal.TerminalApplication;
import com.company.transport.driverterminal.di.AppScope;
import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.utils.AuthorizationManager;
import com.company.transport.driverterminal.utils.PresenterCache;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Module
    public interface Declarations {
        @AppScope
        @Binds
        Context bindContext(TerminalApplication application);
    }

    private final static String TRANSCRYPT_SHARED_PREFERENCES = "com.units.transcrypt.prefs";

    @AppScope
    @Provides
    public TransportCompanyApiClient provideTransportCompanyApiClient() {
        return new TransportCompanyApiClient();
    }

    @AppScope
    @Provides
    public AuthorizationManager provideAuthorizationManager(SharedPreferences preferences) {
        return new AuthorizationManager(preferences);
    }

    @AppScope
    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(TRANSCRYPT_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @AppScope
    @Provides
    PresenterCache providePresenterCache() {
        return new PresenterCache();
    }
}
