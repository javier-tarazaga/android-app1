
package com.tinygrip.android.presentation.view.navigation.presenter;

import android.location.Location;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.tinygrip.android.domain.model.PreviewArea;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
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

    //private final UseCase getUserListUseCase;
    //private final UserModelDataMapper userModelDataMapper;

    @Inject
    public HomePresenter() {
        //this.getUserListUseCase = getUserListUserCase;
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
        //this.getUserListUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadUserList();
        this.loadMap();
    }

    /**
     * Loads and initializes the map
     */
    private void loadMap() {

    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getAreaList();
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

    private void getAreaList() {
        //this.getUserListUseCase.execute(new UserListSubscriber());
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

    private final class GetPrefiewAreasSubscriber extends DefaultSubscriber<PreviewArea> {

        @Override
        public void onCompleted() {
            HomePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            HomePresenter.this.hideViewLoading();
            HomePresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            HomePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(PreviewArea previewArea) {
            super.onNext(previewArea);
        }
    }
}
