package com.tinygrip.android.presentation.view.area.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.model.AreaModel;
import com.tinygrip.android.presentation.view.area.AreaComponent;
import com.tinygrip.android.presentation.view.area.DaggerAreaComponent;
import com.tinygrip.android.presentation.view.area.fragment.AreaFragment;
import com.tinygrip.android.presentation.view.base.BaseActivity;

/**
 * Activity that lets the user see an already created {@link AreaModel}
 */
public class AreaActivity extends BaseActivity implements HasComponent<AreaComponent>, AreaFragment.AreaListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, AreaActivity.class);
  }

  private AreaComponent areaComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_area);

    this.initializeActivity();
    this.initializeInjector();
  }

  @Override public AreaComponent getComponent() {
    return areaComponent;
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity() {
    addFragment(R.id.fl_fragment, AreaFragment.newInstance());
  }

  private void initializeInjector() {
    this.areaComponent = DaggerAreaComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public void onBackClicked() {
    this.finish();
  }
}
