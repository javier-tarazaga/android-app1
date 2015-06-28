package com.tinygrip.ui.gallery;

import com.tinygrip.TinyGripComponent;
import com.tinygrip.ui.gallery.view.GalleryView;

import dagger.Component;

@GalleryScope
@Component(
    dependencies = TinyGripComponent.class,
    modules = GalleryModule.class
)
public interface GalleryComponent extends GalleryView.Injector {
    void inject(GalleryActivity activity);
}
