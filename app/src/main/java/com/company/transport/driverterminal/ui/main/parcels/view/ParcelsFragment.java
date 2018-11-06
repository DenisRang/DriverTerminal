package com.company.transport.driverterminal.ui.main.parcels.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.di.ApplicationComponent;
import com.company.transport.driverterminal.di.DaggerApplicationComponent;
import com.company.transport.driverterminal.ui.base.BaseFragmentView;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsContract;
import com.company.transport.driverterminal.ui.main.parcels.ParcelsType;
import com.company.transport.driverterminal.ui.main.parcels.di.components.ApplicationComponentForParcels;
import com.company.transport.driverterminal.ui.main.parcels.di.components.CompletedParcelsComponent;
import com.company.transport.driverterminal.ui.main.parcels.di.components.DaggerApplicationComponentForParcels;
import com.company.transport.driverterminal.ui.main.parcels.di.components.DaggerCompletedParcelsComponent;
import com.company.transport.driverterminal.ui.main.parcels.di.components.DaggerIncomingParcelsComponent;
import com.company.transport.driverterminal.ui.main.parcels.di.components.DaggerParcelsComponent;
import com.company.transport.driverterminal.ui.main.parcels.di.components.IncomingParcelsComponent;
import com.company.transport.driverterminal.ui.main.parcels.di.components.ParcelsComponent;
import com.company.transport.driverterminal.ui.main.parcels.di.modules.ContextModule;

import java.util.Objects;

import butterknife.BindView;

public class ParcelsFragment extends BaseFragmentView<ParcelsContract.Presenter> implements ParcelsContract.View {
    private static final String ARGUMENT_PARCELS_TYPE = "parcels type";

    @BindView(R.id.recycler_parcel_groups)
    protected RecyclerView recyclerView;
    @BindView(R.id.progress_bar_center)
    protected ProgressBar progressBar;
    @BindView(R.id.text_empty_view)
    protected TextView emptyView;

    private ParcelsAdapter parcelsAdapter;

    public static ParcelsFragment newInstance(@ParcelsType int parcelsType) {
        ParcelsFragment fragment = new ParcelsFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PARCELS_TYPE, parcelsType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getPresenterSavingName() {
        return super.getPresenterSavingName() + String.valueOf(getParcelsType());
    }

    @Override
    protected void inject() {
        ApplicationComponentForParcels appComponent = DaggerApplicationComponentForParcels.builder()
                .contextModule(new ContextModule(getContext()))
                .build();
        ParcelsComponent parcelsComponent = DaggerParcelsComponent.builder()
                .applicationComponentForParcels(appComponent)
                .build();
        @ParcelsType int parcelsType = getArguments().getInt(ARGUMENT_PARCELS_TYPE);
        switch (parcelsType) {
            case ParcelsType.INCOMING:
                IncomingParcelsComponent incomingParcelsComponent = DaggerIncomingParcelsComponent.builder()
                        .parcelsComponent(parcelsComponent)
                        .build();
                presenter = incomingParcelsComponent.getIncomingParcelPresenter();
                return;
            case ParcelsType.COMPLETED:
                CompletedParcelsComponent completedParcelsComponent = DaggerCompletedParcelsComponent.builder()
                        .parcelsComponent(parcelsComponent)
                        .build();
                presenter = completedParcelsComponent.getCompletedParcelPresenter();
                return;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_parcels, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
        attachConfiguredViewToPresenter();
    }

    @Override
    public void notifyParcelsChanged() {
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminate(false);
    }

    @Override
    public void showNoInternetConnection() {
        emptyView.setText(R.string.msg_no_internet);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoParcels() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoParcels() {
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public @ParcelsType int getParcelsType() {
        return getArguments().getInt(ARGUMENT_PARCELS_TYPE);
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        parcelsAdapter = new ParcelsAdapter(presenter);
        recyclerView.setAdapter(parcelsAdapter);
    }
}
