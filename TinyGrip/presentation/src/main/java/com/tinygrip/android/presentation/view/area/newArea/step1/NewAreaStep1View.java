package com.tinygrip.android.presentation.view.area.newArea.step1;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing the step 1 when creating a new Area
 */
public interface NewAreaStep1View {

  /**
   * Simply go back in the stack
   */
  void goBack();

  /**
   * Go back to next step.
   */
  void gotToNextStep();
}
