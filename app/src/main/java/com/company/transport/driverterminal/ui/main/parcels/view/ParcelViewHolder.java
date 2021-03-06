package com.company.transport.driverterminal.ui.main.parcels.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.transportCompanyApi.parcelListResponse.Parcel;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParcelViewHolder extends RecyclerView.ViewHolder implements ParcelsContract.ParcelView {

    @BindView(R.id.text_title)
    protected TextView titleTextView;
    @BindView(R.id.text_time)
    protected TextView timeTextView;
    @BindView(R.id.text_address)
    protected TextView addressTextView;
    @BindView(R.id.text_weight)
    protected TextView weightTextView;
    @BindView(R.id.text_volume)
    protected TextView volumeTextView;

    private ParcelListener listener;
    private Parcel parcel;

    protected ParcelViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setTitleTextView(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void setTimeTextView(String time) {
        timeTextView.setText(time);
    }

    @Override
    public void setAddressTextView(String address) {
        addressTextView.setText(address);
    }

    @Override
    public void setWeightTextView(String weight) {
        weightTextView.setText(weight);
    }

    @Override
    public void setVolumeTextView(String volume) {
        volumeTextView.setText(volume);
    }

    @Override
    @OnClick(R.id.card_parcel)
    public void onParcelClick() {
        listener.onParcelClick(this);
    }

    @Override
    public void setParcelListener(ParcelListener listener) {
        this.listener = listener;
    }

    @Override
    public void navigateToGoogleMaps(Intent intent) {
        itemView.getContext().startActivity(intent);
    }

    @Override
    public void setParcel(Parcel parcel) {
        this.parcel=parcel;
    }

    @Override
    public Parcel getParcel() {
        return parcel;
    }

    public interface ParcelListener {

        void onParcelClick(ParcelsContract.ParcelView parcelView);

    }
}
