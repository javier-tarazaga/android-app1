
package com.tinygrip.android.presentation.view.area.newArea.step1;

import android.support.annotation.NonNull;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.presenter.Presenter;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class NewAreaStep1Presenter implements Presenter<NewAreaStep1View> {

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
