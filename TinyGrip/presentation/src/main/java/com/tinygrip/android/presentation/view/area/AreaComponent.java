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
package com.tinygrip.android.presentation.view.area;

import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.components.ApplicationComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.view.area.fragment.AreaFragment;
import com.tinygrip.android.presentation.view.area.fragment.NewAreaStep1Fragment;
import com.tinygrip.android.presentation.view.area.fragment.NewAreaStep2Fragment;
import com.tinygrip.android.presentation.view.navigation.MainTabNavigationModule;
import dagger.Component;

/**
 * A scope {@link ActivityScope} component.
 * Injects Area specific Fragments.
 */
@ActivityScope @Component(dependencies = { ApplicationComponent.class }, modules = {
    ActivityModule.class, AreaModule.class
}) public interface AreaComponent extends ActivityComponent {
  void inject(NewAreaStep1Fragment newAreaStep1Fragment);
  void inject(NewAreaStep2Fragment newAreaStep2Fragment);
  void inject(AreaFragment areaFragment);
}
