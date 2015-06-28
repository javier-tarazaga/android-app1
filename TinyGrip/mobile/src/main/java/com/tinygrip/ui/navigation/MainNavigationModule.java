package com.tinygrip.ui.navigation;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;

@Module
public class MainNavigationModule {
    private final MainNavigationActivity mainNavigationActivity;

    public MainNavigationModule(MainNavigationActivity mainNavigationActivity) {

        this.mainNavigationActivity = mainNavigationActivity;
    }

    @Provides
    FragmentManager providesFragmentManager() {
        return mainNavigationActivity.getSupportFragmentManager();
    }
}
