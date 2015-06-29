package com.tinygrip.android.ui.gallery;

import com.tinygrip.android.TinyGripComponent;
import com.tinygrip.android.ui.gallery.view.GalleryView;

import dagger.Component;

@GalleryScope
@Component(
    dependencies = TinyGripComponent.class,
    modules = GalleryModule.class
)
public interface GalleryComponent extends GalleryView.Injector {
    void inject(GalleryActivity activity);
}
