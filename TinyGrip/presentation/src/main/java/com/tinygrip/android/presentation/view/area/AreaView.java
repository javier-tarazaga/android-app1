package com.tinygrip.android.presentation.view.area;

import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.presentation.view.LoadDataView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing an Area
 */
public interface AreaView extends LoadDataView {

  void renderArea(Area area);

  /**
   * Simply go back in the stack
   */
  void goBack();
}
