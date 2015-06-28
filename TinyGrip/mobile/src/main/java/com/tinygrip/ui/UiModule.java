package com.tinygrip.ui;

import android.app.Activity;

import com.tinygrip.base.navigation.activity.ActivityScreenSwitcher;
import com.tinygrip.ui.annotation.ActivityScreenSwitcherServer;

import dagger.Module;
import dagger.Provides;

@Module
public class UiModule {

    @Provides
    @ApplicationScope
    ActivityScreenSwitcher provideActivityScreenSwitcher() {
        return new ActivityScreenSwitcher();
    }

    @Provides
    @ApplicationScope
    @ActivityScreenSwitcherServer
    ActivityHierarchyServer provideActivityScreenSwitcherServer(final ActivityScreenSwitcher screenSwitcher) {
        return new ActivityHierarchyServer.Empty() {
            @Override
            public void onActivityStarted(Activity activity) {
                screenSwitcher.attach(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                screenSwitcher.detach();
            }
        };
    }
}
