package com.tinygrip.android.presentation.view.area;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import javax.inject.Inject;

/**
 * Fragment that shows the first step when creating a new area
 **/
public class AreaFragment extends BaseFragment implements AreaView {

  /**
   * Interface for listening the area events
   */
  public interface AreaListener {
    void onBackClicked();
  }

  @Inject AreaPresenter areaPresenter;

  @Bind(R.id.tb_area) Toolbar toolbar;

  private AreaListener areaListener;

  public static AreaFragment newInstance() {
    return new AreaFragment();
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof AreaActivity) {
      this.areaListener = (AreaActivity) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_area, container, false);
    ButterKnife.bind(this, fragmentView);

    return fragmentView;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    this.initialize();
    this.setupUI();
  }

  @Override public void onResume() {
    super.onResume();
    this.areaPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.areaPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.areaPresenter.destroy();
  }

  private void initialize() {
    this.getComponent(AreaComponent.class).inject(this);
    this.areaPresenter.setView(this);
    this.areaPresenter.initialize();
  }

  private void setupUI() {
    setToolbar();
  }

  private void setToolbar() {
    this.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
    this.toolbar.setNavigationOnClickListener(onToolbarBackClickListener);
  }

  @Override public void goBack() {
    if (this.areaListener != null) {
      this.areaListener.onBackClicked();
    }
  }

  private View.OnClickListener onToolbarBackClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      AreaFragment.this.areaPresenter.onBackClicked();
    }
  };
}
