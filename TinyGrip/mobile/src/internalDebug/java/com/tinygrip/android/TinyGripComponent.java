package com.tinygrip.android;

import com.tinygrip.android.data.DebugDataModule;
import com.tinygrip.android.ui.ApplicationScope;
import com.tinygrip.android.ui.DebugUiModule;

import dagger.Component;

/**
* The core debug component for TinyGrip applications
*/
@ApplicationScope
@Component(modules = { TinyGripAppModule.class, DebugUiModule.class, DebugDataModule.class })
public interface TinyGripComponent extends DebugInternalTinyGripGraph {
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

