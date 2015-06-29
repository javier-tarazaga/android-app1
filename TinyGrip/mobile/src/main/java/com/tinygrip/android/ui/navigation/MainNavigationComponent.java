package com.tinygrip.android.ui.navigation;

import com.tinygrip.android.TinyGripComponent;

import dagger.Component;

@MainNavigationScope
@Component(
        dependencies = TinyGripComponent.class,
        modules = MainNavigationModule.class
)
public interface MainNavigationComponent extends MainNavigationView.Injector {
    void inject(MainNavigationActivity activity);
}
