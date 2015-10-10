package com.tinygrip.android.presentation.view.navigation.view;

import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing the home tab in the main
 */
public interface DiscoverView extends LoadDataView {

  /**
   * Render a map in the UI
   */
  void renderMap();

  /**
   * Render a set of filters which will be applied to the map
   */
  void renderMapFilters();
}
