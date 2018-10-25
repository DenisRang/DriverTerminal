package com.company.transport.driverterminal.transportCompanyApi;

import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface TransportCompanyApi {
    @GET("api/v1/parcels")
    Single<List<Parcel>> getParcelList();
}
