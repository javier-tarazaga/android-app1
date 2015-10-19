
package com.tinygrip.android.presentation.internal.di.modules;

import android.app.Activity;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @ActivityScope
    Activity activity() {
        return this.activity;
    }
}
