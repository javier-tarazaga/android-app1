package com.tinygrip.ui;

import com.tinygrip.base.mvp.Registry;
import com.tinygrip.ui.annotation.ActivityScreenSwitcherServer;
import com.tinygrip.ui.debug.DebugAppContainer;
import com.tinygrip.ui.debug.SocketActivityHierarchyServer;

import dagger.Module;
import dagger.Provides;

@Module(includes = UiModule.class)
public class DebugUiModule {
    @Provides
    @ApplicationScope
    AppContainer provideAppContainer(DebugAppContainer appContainer) {
        return appContainer;
    }

    @Provides
    @ApplicationScope
    ActivityHierarchyServer provideActivityHierarchyServer(@ActivityScreenSwitcherServer ActivityHierarchyServer server) {
        final ActivityHierarchyServer.Proxy proxy = new ActivityHierarchyServer.Proxy();
        proxy.addServer(server);
        proxy.addServer(Registry.SERVER);
        proxy.addServer(new SocketActivityHierarchyServer());
        return proxy;
    }
}
