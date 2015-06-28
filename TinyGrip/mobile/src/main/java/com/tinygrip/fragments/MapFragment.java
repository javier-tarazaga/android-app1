package com.tinygrip.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.MapView;
import com.tinygrip.R;

/**
 * Created by Nazar Zherebetskyi on 28.06.2015.
 */
public class MapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  LayoutInflater.from(getActivity()).inflate(R.layout.map_fragment_layout, null);
        MapView mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        Spinner typeSpinner = (Spinner) view.findViewById(R.id.type_spinner);
        Spinner gradeSpinner = (Spinner) view.findViewById(R.id.grade_spinner);
        String[] types = getActivity().getResources().getStringArray(R.array.type_array);
        String[] grades = getActivity().getResources().getStringArray(R.array.grade_array);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item , types);
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item , grades);
        typeSpinner.setAdapter(typeAdapter);
        gradeSpinner.setAdapter(gradeAdapter);
        return view;
    }
}
