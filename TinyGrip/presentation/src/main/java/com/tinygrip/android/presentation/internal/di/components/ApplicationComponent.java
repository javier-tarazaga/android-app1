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
package com.tinygrip.android.presentation.internal.di.components;

import android.content.Context;
import com.tinygrip.android.data.DataModule;
import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
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
@Component(modules = { ApplicationModule.class, DataModule.class } )
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();

  ApplicationRouter navigator();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  UserRepository userRepository();

  RootRepository rootRepository();
}
