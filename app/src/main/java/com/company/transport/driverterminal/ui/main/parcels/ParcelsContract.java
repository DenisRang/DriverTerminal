package com.company.transport.driverterminal.ui.main.parcels;

import android.content.Context;
import android.content.Intent;

import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.ui.base.BaseModel;
import com.company.transport.driverterminal.ui.base.BasePresenter;
import com.company.transport.driverterminal.ui.base.BaseView;
import com.company.transport.driverterminal.ui.main.parcels.view.ParcelViewHolder;

import io.reactivex.Single;

public interface ParcelsContract {

    interface View extends BaseView {
        void notifyParcelsChanged();

        void showProgressBar();

        void hideProgressBar();

        void showNoInternetConnection();

        void showError(String error);

        void showNoParcels();

        void hideNoParcels();

        @ParcelsType int getParcelsType();
    }

    interface Presenter extends BasePresenter<View> {
        void getParcels();

        void onBindParcel(ParcelViewHolder parcelView, int position);

        int getParcelCount();

    }

    interface Model extends BaseModel {

        Single<Integer> downloadParcels(@ParcelsType int parcelType);

        Parcel getParcel(int position);

        int getParcelCount();

    }

    interface ParcelView {
        void setTitleTextView(String title);

        void setTimeTextView(String time);

        void setAddressTextView(String address);

        void setWeightTextView(String weight);

        void setVolumeTextView(String volume);

        void onParcelClick();

        void setParcelListener(ParcelViewHolder.ParcelListener listener);

        void navigateToGoogleMaps(Intent intent);
    }
}
