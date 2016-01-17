package com.tinygrip.android.presentation.view.area.newArea.step2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.area.AreaComponent;
import com.tinygrip.android.presentation.view.area.newArea.NewAreaActivity;
import com.tinygrip.android.presentation.view.base.BaseFragment;
import javax.inject.Inject;

/**
 * Fragment that shows the second step when creating a new area
 **/
public class NewAreaStep2Fragment extends BaseFragment implements NewAreaStep2View {

  /**
   * Interface for listening new area step2 events
   */
  public interface NewAreaStep2Listener {
    void onBackStep2Clicked();

    void onSaveNewAreaClicked();
  }

  @Inject NewAreaStep2Presenter newAreaStep2Presenter;

  @Bind(R.id.toolbar_new_area_step_2) Toolbar toolbar;

  private NewAreaStep2Listener newAreaStep2Listener;

  public static NewAreaStep2Fragment newInstance() {
    return new NewAreaStep2Fragment();
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof NewAreaActivity) {
      this.newAreaStep2Listener = (NewAreaActivity) activity;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View fragmentView = inflater.inflate(R.layout.fragment_new_area_step_2, container, false);
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
    this.newAreaStep2Presenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.newAreaStep2Presenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.newAreaStep2Presenter.destroy();
  }

  private void initialize() {
    this.getComponent(AreaComponent.class).inject(this);
    this.newAreaStep2Presenter.setView(this);
    this.newAreaStep2Presenter.initialize();
  }

  private void setupUI() {
    setToolbar();
  }

  private void setToolbar() {
    Toolbar.LayoutParams layoutParams =
        new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER);

    TextView stepCountTextView = new TextView(getActivity());
    stepCountTextView.setText("Step 2 of 2");
    stepCountTextView.setTextColor(getResources().getColor(android.R.color.white));
    this.toolbar.addView(stepCountTextView, layoutParams);

    layoutParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
        Gravity.TOP | Gravity.END);

    TextView nextTextView = new TextView(getActivity());
    nextTextView.setText("SAVE");
    nextTextView.setTextColor(getResources().getColor(android.R.color.white));
    nextTextView.setClickable(true);
    nextTextView.setFocusable(true);
    nextTextView.setOnClickListener(onToolbarSaveClickListener);

    this.toolbar.setContentInsetsAbsolute(0, (int) getResources().getDimension(R.dimen.activity_vertical_margin));
    this.toolbar.addView(nextTextView, layoutParams);
    this.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
    this.toolbar.setNavigationOnClickListener(onToolbarBackClickListener);
  }

  @Override public void goBack() {
    if (this.newAreaStep2Listener != null) {
      this.newAreaStep2Listener.onBackStep2Clicked();
    }
  }

  @Override public void saveNewArea() {
    if (this.newAreaStep2Listener != null) {
      this.newAreaStep2Listener.onSaveNewAreaClicked();
    }
  }

  private View.OnClickListener onToolbarSaveClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      NewAreaStep2Fragment.this.newAreaStep2Presenter.onSaveClicked();
    }
  };

  private View.OnClickListener onToolbarBackClickListener = new View.OnClickListener() {
    @Override public void onClick(View v) {
      NewAreaStep2Fragment.this.newAreaStep2Presenter.onBackClicked();
    }
  };
}
