package com.tinygrip.android.ui;

import android.app.Activity;
import android.view.ViewGroup;

import com.tinygrip.android.R;

import com.mattprecious.telescope.TelescopeLayout;
import com.tinygrip.android.data.LumberYard;
import com.tinygrip.android.ui.bugreport.BugReportLens;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Bind;

@ApplicationScope
public final class TelescopeAppContainer implements AppContainer {
    private final LumberYard lumberYard;

    @Inject
    public TelescopeAppContainer(LumberYard lumberYard) {
        this.lumberYard = lumberYard;
    }

    @Bind(R.id.telescope_container)
    TelescopeLayout telescopeLayout;

    @Override
    public ViewGroup get(Activity activity) {
        activity.setContentView(R.layout.internal_activity_frame);
        ButterKnife.bind(this, activity);

        TelescopeLayout.cleanUp(activity); // Clean up any old screenshots.
        telescopeLayout.setLens(new BugReportLens(activity, lumberYard));

        return telescopeLayout;
    }
}
