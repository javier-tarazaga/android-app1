package com.tinygrip.android;

import com.tinygrip.android.data.InternalReleaseDataModule;
import com.tinygrip.android.ui.ApplicationScope;
import com.tinygrip.android.ui.InternalReleaseUiModule;

import dagger.Component;

/**
* The core debug component for tiny grip applications
*/
@ApplicationScope
@Component(modules = { TinyGripAppModule.class, InternalReleaseUiModule.class,
        InternalReleaseDataModule.class })
public interface TinyGripComponent extends InternalTinyGripGraph {
    /**
     * An initializer that creates the graph from an application.
     */
    final class Initializer {
        static TinyGripComponent init(TinyGripApp app) {
            return DaggerTinyGripComponent.builder()
                    .tinyGripAppModule(new TinyGripAppModule(app))
                    .build();
        }
        private Initializer() {} // No instances.
    }
}

