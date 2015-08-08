/**
 * Copyright (C) 2014 android.org. All rights reserved.
 * @author Fernando Cejas (the android coder)
 */
package com.tinygrip.android.presentation.view;

import com.tinygrip.android.presentation.model.UserModel;

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
}
