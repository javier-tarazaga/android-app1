
package com.tinygrip.android.presentation.view.navigation;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.GetRoot;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.repository.RootRepository;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides root related collaborators.
 */
@Module
public class MainTabNavigationModule {

  private final String apiKey;

  public MainTabNavigationModule(String apiKey) {
    this.apiKey = apiKey;
  }

  @Provides @ActivityScope @Named("root") UseCase providesGetRootUserCase(
      RootRepository rootRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    return new GetRoot(this.apiKey, rootRepository, threadExecutor, postExecutionThread);
  }
}