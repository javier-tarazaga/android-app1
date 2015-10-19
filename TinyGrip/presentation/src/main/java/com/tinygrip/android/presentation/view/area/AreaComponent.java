
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
