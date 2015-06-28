package com.tinygrip;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;
import com.tinygrip.base.navigation.activity.ActivityScreenSwitcher;
import com.tinygrip.data.GalleryDatabase;
import com.tinygrip.data.api.GalleryService;
import com.tinygrip.data.api.ImageService;
import com.tinygrip.ui.ActivityHierarchyServer;
import com.tinygrip.ui.AppContainer;


/**
 * A common interface implemented by both the Release and Debug flavored components.
 */
public interface TinyGripGraph {
    void inject(TinyGripApp app);
    AppContainer appContainer();
    Picasso picasso();
    OkHttpClient okHttpClient();
    ActivityScreenSwitcher activityScreenSwitcher();
    GalleryDatabase galleryDatabase();
    GalleryService galleryService();
    ImageService imageService();
    ActivityHierarchyServer activityHierarchyServer();
}
