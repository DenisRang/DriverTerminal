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
    public Single<Integer> downloadParcels(@ParcelsType int parcelType) {
        switch (parcelType){
            case ParcelsType.INCOMING:
                parcels.clear();
                parcels.add(new Parcel(16, "1.2", "2.3", "1", "5", "Main street", "14:20", "open"));
                parcels.add(new Parcel(17, "2", "2", "1.7", "7", "Pushkin street", "20:00", "open"));
                parcels.add(new Parcel(18, "4", "0.4", "2", "4", "Lermontov street", "8:00", "open"));
            break;
            case ParcelsType.COMPLETED:
                parcels.clear();
                parcels.add(new Parcel(3, "1.2", "2.3", "1", "0.45", "Big street", "14:20", "open"));
                parcels.add(new Parcel(5, "2", "2", "1.7", "7", "Red street", "20:00", "open"));
                break;
        }
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
