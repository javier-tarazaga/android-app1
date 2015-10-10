package com.tinygrip.android.presentation.view.navigation.view;

import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing the main view containing the navigation tabs
 */
public interface MainNavigationView extends LoadDataView {

  void renderRootLoadedSuccessful();
}
