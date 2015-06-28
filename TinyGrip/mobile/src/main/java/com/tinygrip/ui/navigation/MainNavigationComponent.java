package com.tinygrip.ui.navigation;

import com.tinygrip.TinyGripComponent;
import com.tinygrip.ui.gallery.GalleryActivity;
import com.tinygrip.ui.image.ImgurImageActivity;
import com.tinygrip.ui.image.ImgurImageModule;
import com.tinygrip.ui.image.ImgurImageScope;
import com.tinygrip.ui.image.ImgurImageView;

import dagger.Component;

@MainNavigationScope
@Component(
        dependencies = TinyGripComponent.class,
        modules = MainNavigationModule.class
)
public interface MainNavigationComponent extends MainNavigationView.Injector {
    void inject(MainNavigationActivity activity);
}
