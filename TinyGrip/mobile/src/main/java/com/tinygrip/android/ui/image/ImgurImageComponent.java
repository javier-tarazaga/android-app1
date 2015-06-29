package com.tinygrip.android.ui.image;

import com.tinygrip.android.TinyGripComponent;

import dagger.Component;

@ImgurImageScope
@Component(
        dependencies = TinyGripComponent.class,
        modules = ImgurImageModule.class
)
public interface ImgurImageComponent extends ImgurImageView.Injector {
    void inject(ImgurImageActivity activity);
}
