package com.company.transport.driverterminal.ui.main.parcels;

import com.company.transport.driverterminal.transportCompanyApi.TransportCompanyApiClient;
import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ParcelsModel implements ParcelsContract.Model {
    private TransportCompanyApiClient api;
    private List<Parcel> parcels = new ArrayList<>();

    @Inject
    ParcelsModel(TransportCompanyApiClient api) {
        this.api = api;
    }

    @Override
    public Single<Integer> downloadParcels() {
        parcels.clear();
        parcels.add(new Parcel(16, "1.2", "2.3", "1", "5", "Main street", "14:20", "open"));
        parcels.add(new Parcel(17, "1.2", "2.3", "1", "5", "Main street", "14:20", "open"));
        parcels.add(new Parcel(18, "1.2", "2.3", "1", "5", "Main street", "14:20", "open"));
        return Single.just(parcels.size());
//        return api.getParcelList()
//                .map(parcelList -> {
//                    this.parcels.clear();
//                    this.parcels.addAll(parcelList);
//                    int size = parcels.size();
//                    return size;
//                });
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