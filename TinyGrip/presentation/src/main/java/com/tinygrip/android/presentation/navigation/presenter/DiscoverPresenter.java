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
package com.tinygrip.android.presentation.navigation.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.PreviewArea;
import com.tinygrip.android.domain.User;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.navigation.view.DiscoverView;
import com.tinygrip.android.presentation.presenter.Presenter;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class DiscoverPresenter extends DefaultSubscriber<List<PreviewArea>> implements Presenter {

  private DiscoverView viewDiscoveryView;

  //private final UseCase getUserListUseCase;
  //private final UserModelDataMapper userModelDataMapper;

  @Inject
  public DiscoverPresenter() {
    //this.getUserListUseCase = getUserListUserCase;
    //this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull DiscoverView view) {
    this.viewDiscoveryView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    //this.getUserListUseCase.unsubscribe();
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize() {
    this.loadUserList();
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getAreaList();
  }

  private void showViewLoading() {
    this.viewDiscoveryView.showLoading();
  }

  private void hideViewLoading() {
    this.viewDiscoveryView.hideLoading();
  }

  private void showViewRetry() {
    this.viewDiscoveryView.showRetry();
  }

  private void hideViewRetry() {
    this.viewDiscoveryView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewDiscoveryView.getContext(),
        errorBundle.getException());
    this.viewDiscoveryView.showError(errorMessage);
  }

  private void getAreaList() {
    //this.getUserListUseCase.execute(new UserListSubscriber());
  }

  private final class UserListSubscriber extends DefaultSubscriber<List<User>> {

    @Override public void onCompleted() {
      DiscoverPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      DiscoverPresenter.this.hideViewLoading();
      DiscoverPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      DiscoverPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<User> users) {
    }
  }
}
