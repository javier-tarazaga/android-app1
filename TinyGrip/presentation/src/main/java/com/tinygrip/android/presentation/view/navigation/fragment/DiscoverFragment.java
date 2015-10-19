
package com.tinygrip.android.presentation.view.navigation.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.navigation.view.DiscoverView;
import com.tinygrip.android.presentation.view.navigation.MainTabNavigationComponent;
import com.tinygrip.android.presentation.view.navigation.presenter.DiscoverPresenter;

import com.tinygrip.android.presentation.view.base.BaseFragment;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a map with a preview of all possible Areas
 */
public class DiscoverFragment extends BaseFragment implements DiscoverView {

  /**
   * Interface for listening user list events.
   */
  public interface DiscoverListener {
    void onAreaClicked();
  }

  @Inject DiscoverPresenter discoverPresenter;

  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.btn_retry) Button bt_retry;

  private DiscoverListener discoverListener;

  public static DiscoverFragment newInstance() {
    DiscoverFragment homeFragment = new DiscoverFragment();
    Bundle args = new Bundle();
    homeFragment.setArguments(args);
    return homeFragment;
  }

  public DiscoverFragment() {
    super();
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof DiscoverListener) {
      this.discoverListener = (DiscoverListener) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_discover, container, false);
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
    this.discoverPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.discoverPresenter.pause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.discoverPresenter.destroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  private void initialize() {
    this.getComponent(MainTabNavigationComponent.class).inject(this);
    this.discoverPresenter.setView(this);
  }

  private void setupUI() {

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
    //this.discoverPresenter.initialize();
  }

  @OnClick(R.id.btn_retry) void onButtonRetryClick() {
    DiscoverFragment.this.loadUserList();
  }
}
