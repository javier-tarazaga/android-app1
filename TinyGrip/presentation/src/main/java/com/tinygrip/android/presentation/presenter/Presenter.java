
package com.tinygrip.android.presentation.presenter;

import android.support.annotation.NonNull;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface Presenter<T> {

  /**
   * Method to set the view associated with this presenter
   *
   * @param view Thew view to associate with the presenter.
   */
  void setView(@NonNull T view);

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onResume() method.
   */
  void resume();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onPause() method.
   */
  void pause();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onDestroy() method.
   */
  void destroy();
}
