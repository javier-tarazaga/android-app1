package com.tinygrip.android.ui;

import com.tinygrip.android.base.mvp.Registry;
import com.tinygrip.android.ui.annotation.ActivityScreenSwitcherServer;

import dagger.Module;
import dagger.Provides;

@Module(includes = UiModule.class)
public final class InternalReleaseUiModule {
    @Provides
    @ApplicationScope
    AppContainer provideAppContainer(TelescopeAppContainer telescopeAppContainer) {
        return telescopeAppContainer;
    }

    @Provides
    @ApplicationScope
    ActivityHierarchyServer provideActivityHierarchyServer(@ActivityScreenSwitcherServer ActivityHierarchyServer server) {
        final ActivityHierarchyServer.Proxy proxy = new ActivityHierarchyServer.Proxy();
        proxy.addServer(server);
        proxy.addServer(Registry.SERVER);
        return proxy;
    }
}
