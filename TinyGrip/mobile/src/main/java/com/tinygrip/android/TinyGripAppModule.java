package com.tinygrip.android;

import android.app.Application;

import com.tinygrip.android.ui.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public final class TinyGripAppModule {
    private final TinyGripApp app;

    public TinyGripAppModule(TinyGripApp app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    Application provideApplication() {
        return app;
    }
}
