
package com.tinygrip.android.presentation.view.area;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.interactor.area.GetPreviewAreas;
import com.tinygrip.android.domain.repository.AreaRepository;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides Area related collaborators.
 */
@Module
public class AreaModule {

  public AreaModule() {}

  @Provides
  @ActivityScope
  @Named("getPreviewAreas")
  UseCase provideGetPreviewAreas(AreaRepository areaRepository,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
    return new GetPreviewAreas(areaRepository, threadExecutor, postExecutionThread);
  }
}