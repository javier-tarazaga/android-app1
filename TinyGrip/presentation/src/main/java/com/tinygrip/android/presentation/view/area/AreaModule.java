
package com.tinygrip.android.presentation.view.area;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.area.GetArea;
import com.tinygrip.android.domain.repository.AreaRepository;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides Area related collaborators.
 */
@Module
public class AreaModule {

    public AreaModule() {}

    @Provides
    @ActivityScope
    GetArea providesGetArea(AreaRepository areaRepository,
                            ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
        return new GetArea(areaRepository, threadExecutor, postExecutionThread);
    }
}