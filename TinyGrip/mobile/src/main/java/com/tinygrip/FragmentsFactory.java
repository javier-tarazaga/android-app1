package com.tinygrip;

import android.support.v4.app.Fragment;

import com.tinygrip.fragments.DiscoverFragment;
import com.tinygrip.fragments.MapFragment;
import com.tinygrip.fragments.OfflineFragment;
import com.tinygrip.fragments.ProfileFragment;
import com.tinygrip.fragments.SettingsFragment;

/**
 * Created by Nazar Zherebetskyi on 28.06.2015.
 */
class FragmentsFactory {
    public static Fragment getFragment(Fragments fragment) {
        switch (fragment) {
            case PROFILE:
                return new ProfileFragment();
            case DISCOVER:
                return new DiscoverFragment();
            case MAP:
                return new MapFragment();
            case OFFLINE:
                return new OfflineFragment();
            case SETTINGS:
                return new SettingsFragment();
        }
        return null;
    }
}
