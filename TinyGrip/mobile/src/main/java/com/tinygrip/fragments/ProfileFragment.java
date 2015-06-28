package com.tinygrip.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinygrip.R;

/**
 * Created by Nazar Zherebetskyi on 28.06.2015.
 */
public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       return LayoutInflater.from(getActivity()).inflate(R.layout.profile_fragment_layout, null);
    }
}
