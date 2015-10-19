/**
 * Copyright (C) 2014 android.org. All rights reserved.
 * @author Fernando Cejas (the android coder)
 */
package com.tinygrip.android.presentation.view.user.view;

import android.content.Context;
import com.tinygrip.android.presentation.model.UserModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user login.
 */
public interface UserLoginView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Simply go up in the stack
     */
    void goUp();

    /**
     * Render the login
     */
    void loginSuccessful(UserModel userModel);

    /**
     * Get a {@link android.content.Context}.
     */
    Context getContext();
}
