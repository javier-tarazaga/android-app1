
package com.tinygrip.android.presentation.view.user.profile;

import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
public interface UserDetailsView extends LoadDataView {
  /**
   * Render a user in the UI.
   *
   * @param user The {@link UserModel} that will be shown.
   */
  void renderUser(UserModel user);

  /**
   * Simply go up in the stack
   */
  void goUp();

  /**
   * Notify any listeners that the logout has executed correctly
   */
  void notifyLogoutSuccessfully();
}
