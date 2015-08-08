package com.tinygrip.android.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.components.DaggerMainTabNavigationComponent;
import com.tinygrip.android.presentation.internal.di.components.MainTabNavigationComponent;
import com.tinygrip.android.presentation.internal.di.modules.MainTabNavigationModule;
import com.tinygrip.android.presentation.presenter.MainPresenter;
import com.tinygrip.android.presentation.view.MainView;
import com.tinygrip.android.presentation.view.fragment.UserListFragment;
import javax.inject.Inject;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity implements MainView,
    HasComponent<ActivityComponent> {

  @Inject MainPresenter mainPresenter;

  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.bt_retry) Button bt_retry;

  private MainTabNavigationComponent mainTabNavigationComponent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    this.initializeInjector();
    this.initialize();
    this.loadRoot();
  }

  private void initializeInjector() {
    this.mainTabNavigationComponent = DaggerMainTabNavigationComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .mainTabNavigationModule(new MainTabNavigationModule("Tk029Lrjt50wPLsU"))
        .build();

    this.mainTabNavigationComponent.inject(this);
  }

  private void initialize() {
    this.mainPresenter.setView(this);
  }

  @Override public ActivityComponent getComponent() {
    return mainTabNavigationComponent;
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context getContext() {
    return MainActivity.this;
  }

  /**
   * Load the root element
   */
  private void loadRoot() {
    this.mainPresenter.initialize();
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    MainActivity.this.loadRoot();
  }

  @Override public void renderRootLoadedSuccessful() {

  }
}
