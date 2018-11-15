package com.company.transport.driverterminal.ui.main.parcels;

import android.os.SystemClock;

import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.utils.AuthorizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;

public class ParcelsModel implements ParcelsContract.Model {
    private TransportCompanyApiClient apiClient;
    private String authToken;
    private List<Parcel> parcels = new ArrayList<>();

    @Inject
    ParcelsModel(TransportCompanyApiClient apiClient, AuthorizationManager authorizationManager) {
        this.apiClient = apiClient;
        authToken = authorizationManager.getAuthToken();
    }

    @Override
    public Single<Integer> downloadParcels(@ParcelsType int parcelType) {
        String state = null;
        switch (parcelType) {
            case ParcelsType.INCOMING:
                state = "Processing";
                break;
            case ParcelsType.COMPLETED:
                state = "Received";
                break;
        }
        return apiClient.getParcelList(authToken, state)
                .map(parcels -> {
                    this.parcels.clear();
                    this.parcels.addAll(parcels);
                    int size = parcels.size();
                    return size;
                });
    }

    @Override
    public Parcel getParcel(int position) {
        return parcels.get(position);
    }

    @Override
    public int getParcelCount() {
        return parcels.size();
    }

}
