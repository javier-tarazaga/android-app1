/**
 * Copyright (C) 2014 android.org. All rights reserved.
 *
 * @author Fernando Cejas (the android coder)
 */
package com.tinygrip.android.presentation.navigation.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.tinygrip.android.R;
import com.tinygrip.android.presentation.navigation.MainTabNavigationComponent;
import com.tinygrip.android.presentation.navigation.presenter.HomePresenter;
import com.tinygrip.android.presentation.navigation.view.HomeView;

import com.tinygrip.android.presentation.view.fragment.BaseFragment;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a map with a preview of all possible Areas
 */
public class HomeFragment extends BaseFragment implements HomeView {

  /**
   * Interface for listening user list events.
   */
  public interface HomeListener {
    void onAreaClicked();
  }

  @Inject HomePresenter homePresenter;

  //@Bind(R.id.relative_progress) RelativeLayout rl_progress;
  //@Bind(R.id.relative_retry) RelativeLayout rl_retry;
  //@Bind(R.id.button_retry) Button bt_retry;

  private HomeListener homeListener;

  public HomeFragment() {
    super();
  }

  public static HomeFragment newInstance() {
    HomeFragment homeFragment = new HomeFragment();
    Bundle args = new Bundle();
    homeFragment.setArguments(args);
    return homeFragment;
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof HomeListener) {
      this.homeListener = (HomeListener) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, fragmentView);
    setupUI();

    return fragmentView;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    this.initialize();
    this.loadUserList();
  }

  @Override public void onResume() {
    super.onResume();
    this.homePresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.homePresenter.pause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.homePresenter.destroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  private void initialize() {
    this.getComponent(MainTabNavigationComponent.class).inject(this);
    this.homePresenter.setView(this);
  }

  private void setupUI() {

  }

  @Override public void showLoading() {
    //this.rl_progress.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    //this.rl_progress.setVisibility(View.GONE);
  }

  @Override public void showRetry() {
    //this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    //this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void renderMap() {

  }

  @Override public void renderMapFilters() {

  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context getContext() {
    return this.getActivity().getApplicationContext();
  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    //this.homePresenter.initialize();
  }

  //@OnClick(R.id.button_retry) void onButtonRetryClick() {
  //  HomeFragment.this.loadUserList();
  //}
}
