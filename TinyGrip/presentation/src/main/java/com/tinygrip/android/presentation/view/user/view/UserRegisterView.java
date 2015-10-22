
package com.tinygrip.android.presentation.view.user.view;

import android.content.Context;
import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user registration.
 */
public interface UserRegisterView extends LoadDataView {

    /**
     * Simply go up in the stack
     */
    void goUp();

    /**
     * Render the user registration
     */
    void showRegisterSuccessful();

    /**
     * Get a {@link Context}.
     */
    Context getContext();
}
