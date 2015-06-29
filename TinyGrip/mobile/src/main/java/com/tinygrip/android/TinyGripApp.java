package com.tinygrip.android;

import android.app.Application;
import android.content.Context;

import com.tinygrip.android.ui.ActivityHierarchyServer;

import javax.inject.Inject;

import timber.log.Timber;

public class TinyGripApp extends Application {
    private TinyGripComponent component;

    @Inject
    ActivityHierarchyServer activityHierarchyServer;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO Crashlytics.start(this);
            // TODO Timber.plant(new CrashlyticsTree());
        }

        buildComponentAndInject();

        registerActivityLifecycleCallbacks(activityHierarchyServer);
    }

    public void buildComponentAndInject() {
        component = TinyGripComponent.Initializer.init(this);
        component.inject(this);
    }

    public TinyGripComponent component() {
        return component;
    }

    public static TinyGripApp get(Context context) {
        return (TinyGripApp) context.getApplicationContext();
    }
}
