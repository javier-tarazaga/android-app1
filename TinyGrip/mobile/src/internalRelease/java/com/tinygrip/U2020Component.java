package com.tinygrip;

import dagger.Component;
import ru.ltst.u2020mvp.data.InternalReleaseDataModule;
import ru.ltst.u2020mvp.ui.ApplicationScope;
import ru.ltst.u2020mvp.ui.InternalReleaseUiModule;

/**
* The core debug component for u2020 applications
*/
@ApplicationScope
@Component(modules = { TinyGripAppModule.class, InternalReleaseUiModule.class,
        InternalReleaseDataModule.class })
public interface U2020Component extends InternalTinyGripGraph {
    /**
     * An initializer that creates the graph from an application.
     */
    final class Initializer {
        static U2020Component init(TinyGripApp app) {
            return DaggerU2020Component.builder()
                    .u2020AppModule(new TinyGripAppModule(app))
                    .build();
        }
        private Initializer() {} // No instances.
    }
}

