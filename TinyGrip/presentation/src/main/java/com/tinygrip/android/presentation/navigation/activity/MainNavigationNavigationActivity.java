package com.tinygrip.android.presentation.navigation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.navigation.DaggerMainTabNavigationComponent;
import com.tinygrip.android.presentation.navigation.MainTabNavigationComponent;
import com.tinygrip.android.presentation.navigation.MainTabNavigationModule;
import com.tinygrip.android.presentation.navigation.adapter.MainTabNavigationAdapter;
import com.tinygrip.android.presentation.navigation.fragment.HomeFragment;
import com.tinygrip.android.presentation.navigation.view.MainNavigationView;
import com.tinygrip.android.presentation.presenter.MainPresenter;
import com.tinygrip.android.presentation.view.activity.BaseActivity;
import com.tinygrip.android.presentation.view.fragment.UserDetailsFragment;
import javax.inject.Inject;

/**
 * Main application screen. This is the app entry point.
 */
public class MainNavigationNavigationActivity extends BaseActivity implements MainNavigationView,
    HasComponent<ActivityComponent> {

  @Inject MainPresenter mainPresenter;

  @Bind(R.id.toolbar_main_nav_tabs) Toolbar toolbarMain;
  @Bind(R.id.tablayout_main_nav_tabs) TabLayout tabLayout;
  @Bind(R.id.viewpager_main_nav_tabs) ViewPager viewPager;
  @Bind(R.id.relative_progress) RelativeLayout relativeProgress;
  @Bind(R.id.relative_retry) RelativeLayout relativeRetry;
  @Bind(R.id.button_retry) Button buttonRetry;

  private MainTabNavigationComponent mainTabNavigationComponent;

  private MainTabNavigationAdapter tabsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    this.initializeInjector();
    this.initialize();
    this.setupUI();
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

  private void setupUI() {
    this.toolbarMain.inflateMenu(R.menu.menu_main_activity);

    this.tabsAdapter = new MainTabNavigationAdapter(getSupportFragmentManager(), this);
    this.viewPager.setAdapter(tabsAdapter);
    this.tabLayout.setupWithViewPager(viewPager);
  }

  @Override public ActivityComponent getComponent() {
    return mainTabNavigationComponent;
  }

  @Override public void showLoading() {
    this.relativeProgress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    this.relativeProgress.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    this.relativeRetry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.relativeRetry.setVisibility(View.GONE);
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context getContext() {
    return MainNavigationNavigationActivity.this;
  }

  /**
   * Load the root element
   */
  private void loadRoot() {
    this.mainPresenter.initialize();
  }

  @OnClick(R.id.button_retry) void onButtonRetryClick() {
    MainNavigationNavigationActivity.this.loadRoot();
  }

  @Override public void renderRootLoadedSuccessful() {

  }
}
