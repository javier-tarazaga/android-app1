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
package com.tinygrip.android.presentation.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.Root;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.view.MainView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class MainPresenter extends DefaultSubscriber<Root> implements Presenter {

  private MainView viewMainView;

  private final UseCase getRootUseCase;

  @Inject
  public MainPresenter(@Named("root") UseCase getRootUseCase) {
    this.getRootUseCase = getRootUseCase;
  }

  public void setView(@NonNull MainView view) {
    this.viewMainView = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getRootUseCase.unsubscribe();
  }

  /**
   * Initializes the presenter by start retrieving the root
   */
  public void initialize() {
    this.loadRoot();
  }

  /**
   * Loads Root.
   */
  private void loadRoot() {
    this.hideViewRetry();
    this.showViewLoading();
    this.getRoot();
  }

  private void showViewLoading() {
    this.viewMainView.showLoading();
  }

  private void hideViewLoading() {
    this.viewMainView.hideLoading();
  }

  private void showViewRetry() {
    this.viewMainView.showRetry();
  }

  private void hideViewRetry() {
    this.viewMainView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewMainView.getContext(),
        errorBundle.getException());
    this.viewMainView.showError(errorMessage);
  }

  private void showRootLoadedInView() {
    this.viewMainView.renderRootLoadedSuccessful();
  }


  private void getRoot() {
    this.getRootUseCase.execute(new RootSubscriber());
  }

  private final class RootSubscriber extends DefaultSubscriber<Root> {

    @Override public void onCompleted() {
      MainPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      MainPresenter.this.hideViewLoading();
      MainPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      MainPresenter.this.showViewRetry();
    }

    @Override public void onNext(Root root) {
      MainPresenter.this.showRootLoadedInView();
    }
  }
}
