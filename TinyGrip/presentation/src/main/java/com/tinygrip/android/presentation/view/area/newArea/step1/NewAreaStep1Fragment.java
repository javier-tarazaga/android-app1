package com.tinygrip.android.presentation.view.area.newArea.step1;

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
 * Fragment that shows the first step when creating a new area
 **/
public class NewAreaStep1Fragment extends BaseFragment implements NewAreaStep1View {

    /**
     * Interface for listening new area step1 events
     */
    public interface NewAreaStep1Listener {
        void onBackStep1Clicked();
        void onNextStepClicked();
    }

    @Inject
    NewAreaStep1Presenter newAreaStep1Presenter;

    @Bind(R.id.tb_new_area_step_1)
    Toolbar toolbar;

    private NewAreaStep1Listener newAreaStep1Listener;

    public static NewAreaStep1Fragment newInstance() {
        return new NewAreaStep1Fragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof NewAreaActivity) {
            this.newAreaStep1Listener = (NewAreaActivity) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_new_area_step_1, container, false);
        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
        this.setupUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.newAreaStep1Presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.newAreaStep1Presenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.newAreaStep1Presenter.destroy();
    }

    private void initialize() {
        this.getComponent(AreaComponent.class).inject(this);
        this.newAreaStep1Presenter.setView(this);
        this.newAreaStep1Presenter.initialize();
    }

    private void setupUI() {
        setToolbar();
    }

    private void setToolbar() {
        Toolbar.LayoutParams layoutParams =
            new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                                     Gravity.CENTER);

        TextView stepCountTextView = new TextView(getActivity());
        stepCountTextView.setText("Step 1 of 2");
        stepCountTextView.setTextColor(getResources().getColor(android.R.color.white));
        this.toolbar.addView(stepCountTextView, layoutParams);

        layoutParams = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                Gravity.TOP | Gravity.END);

        TextView nextTextView = new TextView(getActivity());
        nextTextView.setText("NEXT");
        nextTextView.setTextColor(getResources().getColor(android.R.color.white));
        nextTextView.setClickable(true);
        nextTextView.setFocusable(true);
        nextTextView.setOnClickListener(onToolbarNextClickListener);
        this.toolbar.addView(nextTextView, layoutParams);

        this.toolbar.setContentInsetsAbsolute(0, (int) getResources().getDimension(R.dimen.activity_vertical_margin));
        this.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        this.toolbar.setNavigationOnClickListener(onToolbarBackClickListener);
    }

    @Override
    public void goBack() {
        if (this.newAreaStep1Listener != null) {
            this.newAreaStep1Listener.onBackStep1Clicked();
        }
    }

    @Override
    public void gotToNextStep() {
        if (this.newAreaStep1Listener != null) {
            this.newAreaStep1Listener.onNextStepClicked();
        }
    }

    private View.OnClickListener onToolbarNextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NewAreaStep1Fragment.this.newAreaStep1Presenter.onNextClicked();
        }
    };

    private View.OnClickListener onToolbarBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NewAreaStep1Fragment.this.newAreaStep1Presenter.onBackClicked();
        }
    };
}
