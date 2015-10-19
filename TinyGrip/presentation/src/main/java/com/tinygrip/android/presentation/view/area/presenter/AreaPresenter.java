
package com.tinygrip.android.presentation.view.area.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.area.view.AreaView;
import com.tinygrip.android.presentation.view.area.view.NewAreaStep1View;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class AreaPresenter implements Presenter {

  private AreaView areaView;

  //private final UseCase getUserDetailsUseCase;
  //private final UserModelDataMapper userModelDataMapper;

  //@Inject
  //public NewAreaStep1Presenter(@Named("userDetails") UseCase getUserDetailsUseCase,
  //    UserModelDataMapper userModelDataMapper) {
  //  this.getUserDetailsUseCase = getUserDetailsUseCase;
  //  this.userModelDataMapper = userModelDataMapper;
  //}

  @Inject
  public AreaPresenter() {
    // Empty
  }

  public void setView(@NonNull AreaView view) {
    this.areaView = view;
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
    this.areaView.goBack();
  }
}
