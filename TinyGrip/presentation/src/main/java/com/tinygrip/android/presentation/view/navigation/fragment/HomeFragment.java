
package com.tinygrip.android.presentation.view.navigation.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.maps.MapView;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import com.tinygrip.android.presentation.view.main.MainActivityComponent;
import com.tinygrip.android.presentation.view.navigation.presenter.HomePresenter;
import com.tinygrip.android.presentation.view.navigation.view.HomeView;
import javax.inject.Inject;

/**
 * Fragment that shows a map with a preview of all possible Areas
 */
public class HomeFragment extends BaseFragment implements HomeView {

    /**
     * Interface for listening user events.
     */
    public interface HomeListener {

        void onNewAreaClicked();
    }

    @Inject
    HomePresenter homePresenter;

    @Bind(R.id.map_view)
    MapView mapView;

    //@Bind(R.id.relative_retry) RelativeLayout rl_retry;
    //@Bind(R.id.button_retry) Button bt_retry;

    private HomeListener homeListener;

    public HomeFragment() {
        super();
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof HomeListener) {
            this.homeListener = (HomeListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, fragmentView);
        setupUI(savedInstanceState);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mapView.onResume();
        this.homePresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
        this.homePresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.homePresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mapView.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void initialize() {
        this.getComponent(MainActivityComponent.class).inject(this);
        this.homePresenter.setView(this);
        this.homePresenter.initialize();
    }

    private void setupUI(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
    }

    @Override
    public void showLoading() {
        //this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        //this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        //this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        //this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void createNewArea() {
        if (this.homeListener != null) {
            this.homeListener.onNewAreaClicked();
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return this.getActivity().getApplicationContext();
    }

    @OnClick(R.id.fab_new_area)
    void onNewAreaClicked() {
        this.homePresenter.onCreateNewAreaClicked();
    }

    /**
     * Load Google Maps
     */
    @Override
    public void initializeMap() {
        // Gets to GoogleMap from the MapView and does initialization stuff
        this.mapView.getMapAsync(this.homePresenter);
    }
}
