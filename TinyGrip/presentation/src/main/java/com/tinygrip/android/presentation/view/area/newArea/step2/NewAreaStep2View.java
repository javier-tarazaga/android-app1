package com.tinygrip.android.presentation.view.area.newArea.step2;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing the step 2 when creating a new Area
 */
public interface NewAreaStep2View {

  /**
   * Simply go back in the stack
   */
  void goBack();

  /**
   * Save the new area and proceed to the next screen
   */
  void saveNewArea();
}
