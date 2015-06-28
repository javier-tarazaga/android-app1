package com.tinygrip;

import com.tinygrip.data.LumberYard;

import javax.inject.Inject;

import timber.log.Timber;

public class InternalTinyGripApp extends TinyGripApp {
    @Inject
    LumberYard lumberYard;

    @Override
    public void onCreate() {
        super.onCreate();

        lumberYard.cleanUp();
        Timber.plant(lumberYard.tree());
    }

    @Override
    public void buildComponentAndInject() {
        super.buildComponentAndInject();
        component().inject(this);
    }
}
