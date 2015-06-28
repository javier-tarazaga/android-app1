package com.tinygrip.ui.image;

import com.tinygrip.TinyGripComponent;

import dagger.Component;

@ImgurImageScope
@Component(
        dependencies = TinyGripComponent.class,
        modules = ImgurImageModule.class
)
public interface ImgurImageComponent extends ImgurImageView.Injector {
    void inject(ImgurImageActivity activity);
}
