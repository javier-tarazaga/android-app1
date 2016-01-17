
package com.tinygrip.android.presentation.view.user.profile;

import android.content.Context;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user login.
 */
public interface UserProfileView {

    /**
     * Show the user login view
     */
    void showUserLoginView();

    /**
     * Show the user details view
     */
    void showUserDetailsView();

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Get a {@link Context}.
     */
    Context getContext();
}
