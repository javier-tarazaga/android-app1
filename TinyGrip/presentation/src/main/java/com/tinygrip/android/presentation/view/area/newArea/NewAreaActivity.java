package com.tinygrip.android.presentation.view.area.newArea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.model.area.AreaModel;
import com.tinygrip.android.presentation.view.area.AreaComponent;
import com.tinygrip.android.presentation.view.area.DaggerAreaComponent;
import com.tinygrip.android.presentation.view.area.newArea.step1.NewAreaStep1Fragment;
import com.tinygrip.android.presentation.view.area.newArea.step2.NewAreaStep2Fragment;
import com.tinygrip.android.presentation.view.base.BaseActivity;

/**
 * Activity that lets the user create a new {@link AreaModel}.
 */
public class NewAreaActivity extends BaseActivity implements HasComponent<AreaComponent>,
    NewAreaStep1Fragment.NewAreaStep1Listener, NewAreaStep2Fragment.NewAreaStep2Listener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, NewAreaActivity.class);
    }

    private AreaComponent areaComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_area);

        this.initializeActivity();
        this.initializeInjector();
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity() {
        addFragment(R.id.fl_fragment, NewAreaStep1Fragment.newInstance());
    }

    private void initializeInjector() {
        this.areaComponent = DaggerAreaComponent.builder()
                                                .applicationComponent(getApplicationComponent())
                                                .activityModule(getActivityModule())
                                                .build();
    }

    @Override
    public AreaComponent getComponent() {
        return areaComponent;
    }

    @Override
    public void onBackStep1Clicked() {
        this.finish();
    }

    @Override
    public void onNextStepClicked() {
        replaceFragment(R.id.fl_fragment, NewAreaStep2Fragment.newInstance(), true);
    }

    @Override
    public void onBackStep2Clicked() {
        popFragmentBackStack();
    }

    @Override
    public void onSaveNewAreaClicked() {
        // TODO
        //this.applicationRouter.navigateToArea(this);

        // Make sure to remove this activity from the back stack so when going back from the area activity
        // we go to the main view instead
        this.finish();
    }
}
