package com.tinygrip;


import com.tinygrip.data.LumberYard;

public interface InternalTinyGripGraph extends TinyGripGraph {
    LumberYard lumberYard();
    void inject(InternalTinyGripApp debugApp);
}
