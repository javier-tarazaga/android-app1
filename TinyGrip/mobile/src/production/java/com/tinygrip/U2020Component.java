package com.tinygrip;

import com.tinygrip.data.ReleaseDataModule;
import com.tinygrip.ui.ApplicationScope;
import com.tinygrip.ui.ReleaseUiModule;

import dagger.Component;

/**
* The core release component for u2020 applications
*/
@ApplicationScope
@Component(modules = { TinyGripAppModule.class, ReleaseUiModule.class, ReleaseDataModule.class })
public interface U2020Component extends TinyGripGraph {
    /**
     * An initializer that creates the graph from an application.
     */
    final static class Initializer {
        static U2020Component init(TinyGripApp app) {
            return DaggerU2020Component.builder()
                    .u2020AppModule(new TinyGripAppModule(app))
                    .build();
        }
        private Initializer() {} // No instances.
    }
}

