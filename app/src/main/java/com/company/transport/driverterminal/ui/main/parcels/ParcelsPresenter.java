package com.company.transport.driverterminal.ui.main.parcels;

import android.annotation.SuppressLint;

import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.ui.main.parcels.view.ParcelViewHolder;
import com.company.transport.driverterminal.utils.NetworkManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ParcelsPresenter implements ParcelsContract.Presenter, ParcelViewHolder.ParcelListener {

    private ParcelsContract.View view;
    private NetworkManager networkManager;
    private ParcelsContract.Model model;
    private CompositeDisposable disposables = new CompositeDisposable();


    @Inject
    public ParcelsPresenter(NetworkManager networkManager,
                            ParcelsContract.Model model) {
        this.networkManager = networkManager;
        this.model = model;
    }

    @Override
    public void attachView(ParcelsContract.View view, boolean isNew) {
        this.view = view;
        if (isNew) {
            view.showProgressBar();
            getParcels();
        } else {
            view.notifyParcelsChanged();
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void stop() {
        if (disposables != null && disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    @Override
    public void getParcels() {
        if (view == null) return;

        if (networkManager.isConnected()) {
            disposables.add(model.downloadParcels(view.getParcelsType())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            (Integer count) -> {
                                view.hideProgressBar();
                                view.hideNoParcels();
                                if (count > 0) {
                                    view.notifyParcelsChanged();
                                }
                                if (count == 0) {
                                    view.notifyParcelsChanged();
                                    view.showNoParcels();
                                }
                            },
                            (Throwable error) -> {
                                Timber.e(error.getMessage());
                                view.hideProgressBar();
                                view.showError(error.toString());
                            }
                    ));
        } else {
            if (view != null) {
                view.hideProgressBar();
                view.showNoInternetConnection();
            }
        }
    }

    @Override
    public void onBindParcel(ParcelViewHolder parcelView, int position) {
        Parcel parcel = model.getParcel(position);
        parcelView.setTitleTextView(String.format("Parcel #%d", parcel.getId()));
        parcelView.setAddressTextView(parcel.getDestinationAddress());
        parcelView.setTimeTextView(parcel.getDeliveryTime());
        parcelView.setWeightTextView(parcel.getWeight());
        parcelView.setVolumeTextView(String.format("%s x %s x %s",
                parcel.getWidth(),
                parcel.getLength(),
                parcel.getHeight()));
        parcelView.setParcelListener(this);
    }

    @Override
    public int getParcelCount() {
        return model.getParcelCount();
    }

    @Override
    public void onParcelClick(ParcelsContract.ParcelView parcelView) {

    }
}
