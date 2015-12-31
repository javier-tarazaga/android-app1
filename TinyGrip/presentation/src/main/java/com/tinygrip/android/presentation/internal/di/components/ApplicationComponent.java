
package com.tinygrip.android.presentation.internal.di.components;

import android.content.Context;
import com.tinygrip.android.data.DataModule;
import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.repository.AreaRepository;
import com.tinygrip.android.domain.repository.RootRepository;
import com.tinygrip.android.domain.repository.UserRepository;
import com.tinygrip.android.presentation.internal.di.modules.ApplicationModule;
import com.tinygrip.android.presentation.ApplicationRouter;
import com.tinygrip.android.presentation.view.base.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ApplicationRouter navigator();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    UserRepository userRepository();

    RootRepository rootRepository();

    AreaRepository areaRepository();
}
