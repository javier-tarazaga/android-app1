
package com.tinygrip.android.presentation.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.model.Root;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.view.navigation.view.MainNavigationView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class MainNavigationPresenter extends DefaultSubscriber<Root> implements Presenter<MainNavigationView> {

  private MainNavigationView viewMainNavigationView;

  private final UseCase getRootUseCase;

  @Inject
  public MainNavigationPresenter(@Named("root") UseCase getRootUseCase) {
    this.getRootUseCase = getRootUseCase;
  }

  public void setView(@NonNull MainNavigationView view) {
    this.viewMainNavigationView = view;
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
    this.viewMainNavigationView.showLoading();
  }

  private void hideViewLoading() {
    this.viewMainNavigationView.hideLoading();
  }

  private void showViewRetry() {
    this.viewMainNavigationView.showRetry();
  }

  private void hideViewRetry() {
    this.viewMainNavigationView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewMainNavigationView.getContext(),
        errorBundle.getException());
    this.viewMainNavigationView.showError(errorMessage);
  }

  private void showRootLoadedInView() {
    this.viewMainNavigationView.renderRootLoadedSuccessful();
  }


  private void getRoot() {
    this.getRootUseCase.execute(new RootSubscriber());
  }

  private final class RootSubscriber extends DefaultSubscriber<Root> {

    @Override public void onCompleted() {
      MainNavigationPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      MainNavigationPresenter.this.hideViewLoading();
      MainNavigationPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      MainNavigationPresenter.this.showViewRetry();
    }

    @Override public void onNext(Root root) {
      MainNavigationPresenter.this.showRootLoadedInView();
    }
  }
}
