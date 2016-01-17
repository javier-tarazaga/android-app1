package com.tinygrip.android.presentation.view.area.map;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tinygrip.android.R;
import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import com.tinygrip.android.presentation.view.main.MainActivityComponent;
import java.util.HashMap;
import javax.inject.Inject;

/**
 * Fragment that a map containing area related items
 **/
public class AreaMapFragment extends BaseFragment implements AreaMapView, OnMapReadyCallback {

    @Bind(R.id.map_view)
    MapView mapView;

    @Inject
    AreaMapPresenter presenter;

    /**
     * Interface for listening area map events.
     */
    public interface AreaMapListener {
        void goToArea(PreviewAreaModel model);
    }

    private AreaMapListener mapListener;
    private GoogleMap map;
    private HashMap<String, PreviewAreaModel> markers = new HashMap<>();

    public static AreaMapFragment newInstance() {
        AreaMapFragment areaMapFragment = new AreaMapFragment();
        Bundle args = new Bundle();
        areaMapFragment.setArguments(args);
        return areaMapFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AreaMapListener) {
            this.mapListener = (AreaMapListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_area_map, container, false);
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
        this.presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
        this.presenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.destroy();
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

    @Override
    public void initializeMap() {
        // Gets to GoogleMap from the MapView and does initialization stuff
        this.mapView.getMapAsync(this);
    }

    @Override
    public void renderPreviewAreas(DataPage<PreviewAreaModel> model) {
        if (model != null) {

            for (PreviewAreaModel area : model.getItems()) {
                if (area.getLocation() != null) {
                    MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(area.getLocation().getLatitude(), area.getLocation().getLongitude()))
                        .title(area.getName());

                    this.markers.put(this.map.addMarker(markerOptions).getId(), area);
                    this.map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            PreviewAreaModel previewArea = markers.get(marker.getId());
                            AreaMapFragment.this.presenter.onMarkerClicked(previewArea);

                            return true;
                        }
                    });
                }
            }
        }
    }

    @Override
    public void goToArea(PreviewAreaModel model) {
        if (this.mapListener != null) {
            this.mapListener.goToArea(model);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        this.map.getUiSettings().setMyLocationButtonEnabled(false);
        this.map.setMyLocationEnabled(true);
        this.map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                AreaMapFragment.this.presenter.onMyLocationChange(location);
                //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
            }
        });
    }

    private void initialize() {
        this.getComponent(MainActivityComponent.class).inject(this);
        this.presenter.setView(this);
        this.presenter.initialize();
    }

    private void setupUI(Bundle savedInstanceState) {
        this.mapView.onCreate(savedInstanceState);
    }



}
