
package com.tinygrip.android.presentation.view.navigation.presenter;

import android.location.Location;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.interactor.area.GetPreviewAreas;
import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.domain.model.PreviewArea;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.navigation.view.HomeView;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class HomePresenter extends DefaultSubscriber<List<PreviewArea>> implements Presenter, OnMapReadyCallback {

    private GoogleMap map;
    private HomeView viewHomeView;

    private final UseCase getPreviewAreasUseCase;
    //private final UserModelDataMapper userModelDataMapper;

    @Inject
    public HomePresenter(GetPreviewAreas getPreviewAreasUseCase) {
        this.getPreviewAreasUseCase = getPreviewAreasUseCase;
        //this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull HomeView view) {
        this.viewHomeView = view;
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {
        this.getPreviewAreasUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving the preview areas list.
     */
    public void initialize() {
        this.initializeMap();
        this.loadPreviewAreas();
    }

    /**
     * Loads and initializes the map
     */
    private void initializeMap() {
        this.viewHomeView.initializeMap();
    }

    /**
     * Loads all Preview areas.
     */
    private void loadPreviewAreas() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getPreviewAreaList();
    }

    public void onCreateNewAreaClicked() {
        this.viewHomeView.createNewArea();
    }

    private void showViewLoading() {
        this.viewHomeView.showLoading();
    }

    private void hideViewLoading() {
        this.viewHomeView.hideLoading();
    }

    private void showViewRetry() {
        this.viewHomeView.showRetry();
    }

    private void hideViewRetry() {
        this.viewHomeView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewHomeView.getContext(),
                                                         errorBundle.getException());
        this.viewHomeView.showError(errorMessage);
    }

    private void showPreviewAreasInMap(DataPage<PreviewArea> previewAreaDataPage) {
        for (PreviewArea area : previewAreaDataPage.getItems()) {
            this.map.addMarker(new MarkerOptions()
                                   .position(
                                       new LatLng(area.getLocation().getLatitude(), area.getLocation().getLongitude()))
                                   .title(area.getName()));
        }
    }

    private void getPreviewAreaList() {
        this.getPreviewAreasUseCase.execute(new GetPreviewAreasSubscriber());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        this.map.getUiSettings().setMyLocationButtonEnabled(false);
        this.map.setMyLocationEnabled(true);
        this.map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
            }
        });
    }

    private final class GetPreviewAreasSubscriber extends DefaultSubscriber<DataPage<PreviewArea>> {

        @Override
        public void onCompleted() {
            HomePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);

            HomePresenter.this.hideViewLoading();
            HomePresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            HomePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(DataPage<PreviewArea> previewAreaDataPage) {
            super.onNext(previewAreaDataPage);

            HomePresenter.this.showPreviewAreasInMap(previewAreaDataPage);
        }
    }
}
