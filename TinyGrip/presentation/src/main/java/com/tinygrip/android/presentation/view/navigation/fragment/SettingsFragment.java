
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
import com.tinygrip.android.presentation.view.navigation.view.HomeView;

import com.tinygrip.android.presentation.view.navigation.presenter.SettingsPresenter;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import com.tinygrip.android.presentation.view.navigation.view.SettingsView;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that shows a map with a preview of all possible Areas
 */
public class SettingsFragment extends BaseFragment implements SettingsView {

  /**
   * Interface for listening user list events.
   */
  public interface SettingsListener {
    void onAreaClicked();
  }

  @Inject SettingsPresenter settingsPresenter;

  private SettingsListener settingsListener;

  public SettingsFragment() {
    super();
  }

  public static SettingsFragment newInstance() {
    SettingsFragment homeFragment = new SettingsFragment();
    Bundle args = new Bundle();
    homeFragment.setArguments(args);
    return homeFragment;
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof SettingsListener) {
      this.settingsListener = (SettingsListener) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_settings, container, false);
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
    this.settingsPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.settingsPresenter.pause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.settingsPresenter.destroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  private void initialize() {
    this.getComponent(MainTabNavigationComponent.class).inject(this);
    this.settingsPresenter.setView(this);
  }

  private void setupUI() {

  }

  /**
   * Loads all users.
   */
  private void loadUserList() {
    //this.settingsPresenter.initialize();
  }
}
