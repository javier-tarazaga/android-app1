package com.tinygrip.android.presentation.view.area.map;

import android.location.Location;
import android.support.annotation.NonNull;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.presenter.Presenter;
import javax.inject.Inject;

public class AreaMapPresenter implements Presenter<AreaMapView> {

    private AreaMapView mapView;

    @Inject
    public AreaMapPresenter() {
    }

    @Override
    public void setView(@NonNull AreaMapView view) {
        this.mapView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    /**
     * Initializes the presenter by start retrieving the preview areas list.
     */
    public void initialize() {
        this.initializeMap();
    }

    public void onMarkerClicked(PreviewAreaModel previewArea) {
        this.mapView.goToArea(previewArea);
    }

    public void onMyLocationChange(Location location) {

    }

    private void initializeMap() {
        this.mapView.initializeMap();
    }
}
