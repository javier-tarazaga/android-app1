
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
import com.tinygrip.android.presentation.view.navigation.MainTabNavigationComponent;

import com.tinygrip.android.presentation.view.navigation.presenter.ProfilePresenter;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import com.tinygrip.android.presentation.view.navigation.view.ProfileView;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a map with a preview of all possible Areas
 */
public class ProfileFragment extends BaseFragment implements ProfileView {

  /**
   * Interface for listening user list events.
   */
  public interface ProfileListener {
    void onAreaClicked();
  }

  @Inject ProfilePresenter profilePresenter;

  @Bind(R.id.rl_progress) RelativeLayout rl_progress;
  @Bind(R.id.rl_retry) RelativeLayout rl_retry;
  @Bind(R.id.btn_retry) Button bt_retry;

  private ProfileListener profileListener;

  public ProfileFragment() {
    super();
  }

  public static ProfileFragment newInstance() {
    ProfileFragment homeFragment = new ProfileFragment();
    Bundle args = new Bundle();
    homeFragment.setArguments(args);
    return homeFragment;
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof ProfileListener) {
      this.profileListener = (ProfileListener) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
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
    this.profilePresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.profilePresenter.pause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.profilePresenter.destroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  private void initialize() {
    this.getComponent(MainTabNavigationComponent.class).inject(this);
    this.profilePresenter.setView(this);
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
    //this.profilePresenter.initialize();
  }

  @OnClick(R.id.btn_retry) void onButtonRetryClick() {
    ProfileFragment.this.loadUserList();
  }
}
