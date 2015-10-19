
package com.tinygrip.android.presentation.view.navigation;

import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.components.ApplicationComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.view.navigation.activity.MainNavigationNavigationActivity;
import com.tinygrip.android.presentation.view.navigation.fragment.DiscoverFragment;
import com.tinygrip.android.presentation.view.navigation.fragment.HomeFragment;
import com.tinygrip.android.presentation.view.navigation.fragment.ProfileFragment;
import com.tinygrip.android.presentation.view.navigation.fragment.SettingsFragment;
import com.tinygrip.android.presentation.presenter.MainNavigationPresenter;
import dagger.Component;

/**
 * A scope {@link ActivityScope} component.
 * Injects navigation specific Fragments.
 */
@ActivityScope @Component(dependencies = { ApplicationComponent.class }, modules = {
    ActivityModule.class, MainTabNavigationModule.class})
public interface MainTabNavigationComponent extends ActivityComponent {
  void inject(MainNavigationNavigationActivity mainNavigationActivity);
  void inject(HomeFragment homeFragment);
  void inject(DiscoverFragment discoverFragment);
  void inject(ProfileFragment homeFragment);
  void inject(SettingsFragment settingsFragment);

  MainNavigationPresenter presenter();
}
