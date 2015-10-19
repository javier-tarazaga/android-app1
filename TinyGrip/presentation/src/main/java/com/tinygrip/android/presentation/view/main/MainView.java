package com.tinygrip.android.presentation.view.main;

import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing the main view containing of the app
 */
public interface MainView extends LoadDataView {

    /**
     * View the {@link UserModel} profile/details or logged in view depending on current state.
     */
    void viewProfile();
}
