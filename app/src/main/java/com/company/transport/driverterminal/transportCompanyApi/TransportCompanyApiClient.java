package com.company.transport.driverterminal.transportCompanyApi;

import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.transportCompanyApi.requestAuthorization.AuthorizationRequest;
import com.company.transport.driverterminal.transportCompanyApi.responseAuthorization.AuthorizationResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;

import static com.company.transport.driverterminal.utils.constants.AuthorizationConstants.CLIENT_ID;
import static com.company.transport.driverterminal.utils.constants.AuthorizationConstants.CLIENT_SECRET;
import static com.company.transport.driverterminal.utils.constants.AuthorizationConstants.GRANT_TYPE;

public class TransportCompanyApiClient {

    private TransportCompanyApi api;

    public TransportCompanyApiClient(Retrofit retrofit) {
        api = retrofit.create(TransportCompanyApi.class);
    }

    public Single<AuthorizationResponse> authorize(String username, String password) {
        AuthorizationRequest request = new AuthorizationRequest().withGrantType(GRANT_TYPE)
                .withClientId(CLIENT_ID)
                .withClientSecret(CLIENT_SECRET)
                .withUsername(username)
                .withPassword(password);
        return api.authorize(request);
    }

    public Single<List<Parcel>> getParcelList(String token, String state) {
        return api.getParcelList("Bearer " + token, state);
    }

}
