package com.company.transport.driverterminal.ui.main.parcels.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.company.transport.driverterminal.TerminalApplication;
import com.company.transport.driverterminal.di.AppScope;
import com.company.transport.driverterminal.di.FragmentScope;
import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsModel;
import com.company.transport.driverterminal.utils.AuthorizationManager;
import com.company.transport.driverterminal.utils.NetworkManager;
import com.company.transport.driverterminal.utils.PresenterCache;
import com.company.transport.driverterminal.utils.constants.AuthorizationConstants;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module(includes = ContextModule.class)
public class ApplicationModuleForParcels {
    private final static String TERMINAL_SHARED_PREFERENCES = "com.company.transport.driverterminal.prefs";

    @FragmentScope
    @Provides
    public ParcelsContract.Model parcelsModel(TransportCompanyApiClient api) {
        return new ParcelsModel(api);
    }

    @FragmentScope
    @Provides
    public TransportCompanyApiClient provideTransportCompanyApiClient(Retrofit retrofit) {
        return new TransportCompanyApiClient(retrofit);
    }

    @FragmentScope
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJavaCallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(AuthorizationConstants.TRANSPORT_COMPANY_API_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(okHttpClient)
                .build();
    }

    @FragmentScope
    @Provides
    public OkHttpClient provideRetrofitOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @FragmentScope
    @Provides
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @FragmentScope
    @Provides
    public RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @FragmentScope
    @Provides
    public HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor(message -> Timber.tag("okHttp").d(message)).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @FragmentScope
    @Provides
    public NetworkManager provideNetworkManager(Context context) {
        return new NetworkManager(context);
    }
}
