/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tinygrip.android.presentation.navigation;

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