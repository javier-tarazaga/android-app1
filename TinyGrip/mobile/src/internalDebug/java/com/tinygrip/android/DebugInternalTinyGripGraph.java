package com.tinygrip.android;

import com.tinygrip.android.ui.debug.DebugView;

import retrofit.MockRestAdapter;

public interface DebugInternalTinyGripGraph extends InternalTinyGripGraph {
    MockRestAdapter mockRestAdapter();
    void inject(DebugView view);
}
