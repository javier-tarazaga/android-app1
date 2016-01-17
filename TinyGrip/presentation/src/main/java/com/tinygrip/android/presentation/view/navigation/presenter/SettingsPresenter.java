
package com.tinygrip.android.presentation.view.navigation.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.model.area.PreviewArea;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.navigation.view.SettingsView;
import java.util.List;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class SettingsPresenter extends DefaultSubscriber<List<PreviewArea>> implements Presenter<SettingsView> {

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
