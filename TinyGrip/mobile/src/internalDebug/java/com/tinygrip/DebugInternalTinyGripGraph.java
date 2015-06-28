package com.tinygrip;

import com.tinygrip.ui.debug.DebugView;

import retrofit.MockRestAdapter;

public interface DebugInternalTinyGripGraph extends InternalTinyGripGraph {
    MockRestAdapter mockRestAdapter();
    void inject(DebugView view);
}
