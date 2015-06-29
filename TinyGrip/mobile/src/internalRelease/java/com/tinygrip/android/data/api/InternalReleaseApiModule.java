package com.tinygrip.android.data.api;

import com.tinygrip.android.ui.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;

@Module(includes = ApiModule.class)
public final class InternalReleaseApiModule {
    private static final String CLIENT_ID = "3436c108ccc17d3";

    @Provides
    @ApplicationScope
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(ApiModule.PRODUCTION_API_URL);
    }

    @Provides
    @ApplicationScope
    GalleryService provideGalleryService(RestAdapter restAdapter) {
        return restAdapter.create(GalleryService.class);
    }

    @Provides
    @ApplicationScope
    ImageService provideImageService(RestAdapter restAdapter) {
        return restAdapter.create(ImageService.class);
    }
}
