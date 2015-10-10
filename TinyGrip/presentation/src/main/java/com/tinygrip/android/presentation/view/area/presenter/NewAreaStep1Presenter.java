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
package com.tinygrip.android.presentation.view.area.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.area.view.NewAreaStep1View;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class NewAreaStep1Presenter implements Presenter {

  private NewAreaStep1View newNewAreaStep1View;

  //private final UseCase getUserDetailsUseCase;
  //private final UserModelDataMapper userModelDataMapper;

  //@Inject
  //public NewAreaStep1Presenter(@Named("userDetails") UseCase getUserDetailsUseCase,
  //    UserModelDataMapper userModelDataMapper) {
  //  this.getUserDetailsUseCase = getUserDetailsUseCase;
  //  this.userModelDataMapper = userModelDataMapper;
  //}

  @Inject
  public NewAreaStep1Presenter() {
    // Empty
  }

  public void setView(@NonNull NewAreaStep1View view) {
    this.newNewAreaStep1View = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    //this.getUserDetailsUseCase.unsubscribe();
  }

  /**
   * Initializes the presenter by start retrieving user details.
   */
  public void initialize() {
    //this.loadUserDetails();
  }

  public void onBackClicked() {
    this.newNewAreaStep1View.goBack();
  }

  public void onNextClicked() {
    this.newNewAreaStep1View.gotToNextStep();
  }
}
