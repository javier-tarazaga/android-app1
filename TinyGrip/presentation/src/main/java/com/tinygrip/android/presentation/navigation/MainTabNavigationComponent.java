/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tinygrip.android.presentation.navigation;

import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.components.ApplicationComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.navigation.activity.MainNavigationNavigationActivity;
import com.tinygrip.android.presentation.navigation.fragment.DiscoverFragment;
import com.tinygrip.android.presentation.navigation.fragment.HomeFragment;
import com.tinygrip.android.presentation.navigation.fragment.ProfileFragment;
import com.tinygrip.android.presentation.navigation.fragment.SettingsFragment;
import com.tinygrip.android.presentation.presenter.MainPresenter;
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

  MainPresenter presenter();
}
