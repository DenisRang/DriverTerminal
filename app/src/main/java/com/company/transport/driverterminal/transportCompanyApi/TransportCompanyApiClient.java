package com.company.transport.driverterminal.transportCompanyApi;

import com.company.transport.driverterminal.transportCompanyApi.responseAuthorization.AuthorizationResponseParams;

import io.reactivex.Single;

public class TransportCompanyApiClient {

    public TransportCompanyApiClient() {
    }

    public Single<AuthorizationResponseParams> authorize(String login, String password) {
        TransportCompanyApi api = new TransportCompanyApi(login, password);
        return Single.fromCallable(api);
    }

}
