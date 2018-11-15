package com.company.transport.driverterminal.transportCompanyApi;

import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.transportCompanyApi.requestAuthorization.AuthorizationRequest;
import com.company.transport.driverterminal.transportCompanyApi.responseAuthorization.AuthorizationResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.company.transport.driverterminal.utils.constants.AuthorizationConstants.AUTH_TOKEN_HEADER;

public interface TransportCompanyApi {
    @GET("api/v1/parcels")
    Single<List<Parcel>> getParcelList(@Header(AUTH_TOKEN_HEADER) String token, @Query("state") String state);

    @POST("oauth/token")
    Single<AuthorizationResponse> authorize(@Body AuthorizationRequest authorizationRequest);
}
