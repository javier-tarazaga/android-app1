package com.tinygrip.android;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;
import com.tinygrip.android.base.navigation.activity.ActivityScreenSwitcher;
import com.tinygrip.android.data.GalleryDatabase;
import com.tinygrip.android.data.api.GalleryService;
import com.tinygrip.android.data.api.ImageService;
import com.tinygrip.android.ui.ActivityHierarchyServer;
import com.tinygrip.android.ui.AppContainer;


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
