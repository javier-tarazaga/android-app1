package com.tinygrip.android;

import com.tinygrip.android.data.ReleaseDataModule;
import com.tinygrip.android.ui.ApplicationScope;
import com.tinygrip.android.ui.ReleaseUiModule;

import dagger.Component;

/**
* The core release component for u2020 applications
*/
@ApplicationScope
@Component(modules = { TinyGripAppModule.class, ReleaseUiModule.class, ReleaseDataModule.class })
public interface TinyGripComponent extends TinyGripGraph {
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

