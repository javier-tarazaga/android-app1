package com.tinygrip.android.presentation.view.area.map;

import android.support.annotation.NonNull;
import com.tinygrip.android.presentation.presenter.Presenter;

public class AreaMapPresenter implements Presenter<AreaMapView> {

    private AreaMapView mapView;

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
}
