package com.company.transport.driverterminal.ui.main.parcels.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;

public class ParcelsAdapter extends RecyclerView.Adapter<ParcelViewHolder> {

    private ParcelsContract.Presenter presenter;

    public ParcelsAdapter(ParcelsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ParcelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parcel, parent, false);
        return new ParcelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParcelViewHolder holder, int position) {
        presenter.onBindParcel(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getParcelCount();
    }
}
