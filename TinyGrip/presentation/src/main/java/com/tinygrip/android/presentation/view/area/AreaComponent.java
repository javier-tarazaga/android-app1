
package com.tinygrip.android.presentation.view.area;

import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.components.ApplicationComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.view.area.newArea.step1.NewAreaStep1Fragment;
import com.tinygrip.android.presentation.view.area.newArea.step2.NewAreaStep2Fragment;
import dagger.Component;

/**
 * A scope {@link ActivityScope} component.
 * Injects Area specific Fragments.
 */
@ActivityScope
@Component(dependencies = { ApplicationComponent.class }, modules = {
    ActivityModule.class
})
public interface AreaComponent extends ActivityComponent {

    void inject(NewAreaStep1Fragment newAreaStep1Fragment);

    void inject(NewAreaStep2Fragment newAreaStep2Fragment);

    void inject(AreaFragment areaFragment);
}
