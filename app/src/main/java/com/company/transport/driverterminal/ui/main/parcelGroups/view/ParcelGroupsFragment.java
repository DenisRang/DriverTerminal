//package com.company.transport.driverterminal.ui.main.parcelGroups.view;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.widget.SearchView;
//import android.support.v7.widget.Toolbar;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.company.transport.driverterminal.TerminalApplication;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//import dagger.android.support.AndroidSupportInjection;
//
//public class RootDocGroupsFragment extends Fragment implements RootDocGroupsContract.View,
//        SearchDocGroupsFragment.ToolbarSearchQuery {
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
//    @BindView(R.id.pager)
//    NonSwipeableViewPager viewPager;
//
//    public SearchView searchView;
//    private Unbinder unbinder;
//    private BrowsePagerAdapter browseAdapter;
//    private MainActivity mainActivity;
//    private SearchPagerAdapter searchAdapter;
//    private MenuItem.OnActionExpandListener onActionExpandListener;
//    private PresenterCache presenterCache;
//    private boolean isRestoredPresenter;
//
//    @Inject
//    RootDocGroupsContract.Presenter presenter;
//
//    public RootDocGroupsFragment() {
//        //required
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        presenterCache = TerminalApplication.getPresenterCache();
//        restoreOrCreatePresenter();
//        super.onAttach(context);
//        mainActivity = (MainActivity) context;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_root_document_groups, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        unbinder = ButterKnife.bind(this, view);
//        setHasOptionsMenu(true);
//        setToolbar();
//        setAdapters(view);
//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.setOffscreenPageLimit(3);
//        if (presenter.getIsSearchMode()) {
//            viewPager.setAdapter(searchAdapter);
//        } else {
//            viewPager.setAdapter(browseAdapter);
//        }
//        if (presenter.getCurrentPagerItem() != -1) {
//            viewPager.setCurrentItem(presenter.getCurrentPagerItem());
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (!getActivity().isChangingConfigurations()) {
//            // activity is stopped normally, remove the cached presenter so it's not cached
//            // even if activity gets killed
//            presenterCache.removePresenter(presenter);
//            presenter.stop();
//        } else {
//            presenter.setCurrentPagerItem(viewPager.getCurrentItem());
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
//        presenter.detachView();
//        RefWatcher refWatcher = TranscryptApplication.getRefWatcher(getActivity());
//        refWatcher.watch(this);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.document_groups, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//
//        MenuItem itemSearch = menu.findItem(R.id.action_search);
//        MenuItem itemFilter = menu.findItem(R.id.action_filter);
//        setOnActionExpandListener(itemSearch, itemFilter);
//
//        searchView = (SearchView) itemSearch.getActionView();
//        setOnQueryTextListener(searchView);
//
//        if (presenter.getIsSearchMode()) {
//            if (searchView != null) {
//                itemSearch.expandActionView();
//                searchView.setQuery(presenter.getSearchQuery(), false);
//                searchView.clearFocus();
//                if (presenter.getCurrentPagerItem() != -1) {
//                    viewPager.setCurrentItem(presenter.getCurrentPagerItem());
//                }
//            }
//        }
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_filter) {
//            FragmentManager fm = mainActivity.getSupportFragmentManager();
//            FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
//            FilterFragment fragment = new FilterFragment();
//
//            Bundle args = new Bundle();
//            args.putInt(ArgumentsConstants.groupsType, viewPager.getCurrentItem() + 1);
//            fragment.setArguments(args);
//            transaction.replace(R.id.frame_layout_fragment_container, fragment, FilterFragment.TAG);
//            transaction.commitNow();
//        }
//        return true;
//    }
//
//    @Override
//    public void setSearchViewQuery(String query) {
//        if (searchView != null) {
//            searchView.setQuery(query, false);
//            searchView.clearFocus();
//            searchAdapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void setSearchQuery(String query) {
//        presenter.setSearchQuery(query);
//    }
//
//    @Override
//    public String getSearchQuery() {
//        return presenter.getSearchQuery();
//    }
//
//    public int getCurrentNestedItem() {
//        return viewPager.getCurrentItem();
//    }
//
//    private void setAdapters(View view) {
//        browseAdapter = new BrowsePagerAdapter(view, getChildFragmentManager());
//        searchAdapter = new SearchPagerAdapter(view, getChildFragmentManager());
//    }
//
//    private void setOnActionExpandListener(MenuItem itemSearch, MenuItem itemFilter) {
//        onActionExpandListener = new MenuItem.OnActionExpandListener() {
//
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                presenter.setIsSearchMode(true);
//                viewPager.setAdapter(searchAdapter);
//                viewPager.setOffscreenPageLimit(searchAdapter.getCount());
//                itemFilter.setVisible(false);
//                searchView.requestFocus();
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                presenter.setIsSearchMode(false);
//                presenter.setSearchQuery("");
//                viewPager.setAdapter(browseAdapter);
//                itemFilter.setVisible(true);
//                return true;
//            }
//        };
//        itemSearch.setOnActionExpandListener(onActionExpandListener);
//    }
//
//    private void setOnQueryTextListener(SearchView searchView) {
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                presenter.setSearchQuery(query);
//                presenter.insertLastQuery(presenter.getSearchQuery());
//                searchAdapter.notifyDataSetChanged();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//    }
//
//    private void setToolbar() {
//        toolbar.setTitle(R.string.title_document_groups);
//        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
//    }
//
//
//    private void restoreOrCreatePresenter() {
//        isRestoredPresenter = true;
//        // try to get a cached presenter
//        presenter = presenterCache.getPresenter(getClass().getName());
//        if (presenter == null) {
//            // no cached one found, create a new one
//            isRestoredPresenter = false;
//            AndroidSupportInjection.inject(this);
//            presenterCache.putPresenter(getClass().getName(), presenter);
//        }
//    }
//}