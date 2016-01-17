
package com.tinygrip.android.presentation.view.main;

import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.components.ApplicationComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.presenter.MainNavigationPresenter;
import com.tinygrip.android.presentation.view.area.map.AreaMapFragment;
import com.tinygrip.android.presentation.view.area.map.AreaMapPresenter;
import com.tinygrip.android.presentation.view.navigation.fragment.HomeFragment;
import com.tinygrip.android.presentation.view.navigation.presenter.HomePresenter;
import dagger.Component;

/**
 * A scope {@link ActivityScope} component.
 * Injects navigation specific Fragments.
 */
@ActivityScope
@Component(dependencies = { ApplicationComponent.class }, modules = {
    ActivityModule.class, MainActivityModule.class
})
public interface MainActivityComponent extends ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(HomeFragment homeFragment);
    void inject(AreaMapFragment areaMapFragment);

    MainNavigationPresenter presenter();
    HomePresenter homePresenter();
    AreaMapPresenter areaMapPresenter();
}
