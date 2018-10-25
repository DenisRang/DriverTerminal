package com.company.transport.driverterminal.transportCompanyApi;

import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.transportCompanyApi.responseAuthorization.AuthorizationResponseParams;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class TransportCompanyApiClient {

    private TransportCompanyApi api;

    public TransportCompanyApiClient(Retrofit retrofit) {
        api = retrofit.create(TransportCompanyApi.class);
    }

    public Single<AuthorizationResponseParams> authorize(String login, String password) {
        AuthorizationCallable api = new AuthorizationCallable(login, password);
        return Single.fromCallable(api);
    }

    public Single<List<Parcel>> getParcelList() {
        return api.getParcelList();
    }

}
