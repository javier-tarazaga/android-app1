package com.tinygrip.android.presentation.view.area;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.model.area.AreaModel;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.view.base.BaseActivity;

/**
 * Activity that lets the user see an already created {@link AreaModel}
 */
public class AreaActivity extends BaseActivity implements HasComponent<AreaComponent>, AreaFragment.AreaListener {

    private static final String INTENT_EXTRA_PARAM_PREVIEW_AREA = "com.tinygrip.android.INTENT_PARAM_PREVIEW_AREA";

    private PreviewAreaModel previewArea;

    public static Intent getCallingIntent(Context context, PreviewAreaModel previewArea) {
        Intent intent = new Intent(context, AreaActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_PREVIEW_AREA, previewArea);

        return intent;
    }

    private AreaComponent areaComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        this.initializeActivity();
        this.initializeInjector();
    }

    @Override
    public AreaComponent getComponent() {
        return areaComponent;
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity() {

        this.previewArea = (PreviewAreaModel) getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_PREVIEW_AREA);
        addFragment(R.id.fl_fragment, AreaFragment.newInstance(this.previewArea));
    }

    private void initializeInjector() {
        this.areaComponent = DaggerAreaComponent.builder()
                                                .applicationComponent(getApplicationComponent())
                                                .activityModule(getActivityModule())
                                                .build();
    }

    @Override
    public void onBackClicked() {
        this.finish();
    }
}
