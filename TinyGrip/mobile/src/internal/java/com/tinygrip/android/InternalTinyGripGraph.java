package com.tinygrip.android;


import com.tinygrip.android.data.LumberYard;

public interface InternalTinyGripGraph extends TinyGripGraph {
    LumberYard lumberYard();
    void inject(InternalTinyGripApp debugApp);
}
