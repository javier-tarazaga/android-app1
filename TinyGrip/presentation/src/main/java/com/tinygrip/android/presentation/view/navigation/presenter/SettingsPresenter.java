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
package com.tinygrip.android.presentation.view.navigation.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.PreviewArea;
import com.tinygrip.android.domain.User;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.view.navigation.view.HomeView;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.navigation.view.SettingsView;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class SettingsPresenter extends DefaultSubscriber<List<PreviewArea>> implements Presenter {

  private SettingsView viewSettingsView;

  @Inject
  public SettingsPresenter() {
  }

  public void setView(@NonNull SettingsView view) {
    this.viewSettingsView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
  }

  /**
   * Initializes the presenter
   */
  public void initialize() {
  }
}
