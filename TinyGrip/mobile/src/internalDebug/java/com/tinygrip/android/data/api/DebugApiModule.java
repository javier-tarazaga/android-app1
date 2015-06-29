package com.tinygrip.android.data.api;

import android.content.SharedPreferences;

import com.tinygrip.android.data.ApiEndpoint;
import com.tinygrip.android.data.IsMockMode;
import com.tinygrip.android.data.api.model.MockImageService;
import com.tinygrip.android.data.prefs.StringPreference;
import com.tinygrip.android.ui.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import retrofit.android.AndroidMockValuePersistence;

@Module(includes = ApiModule.class)
public final class DebugApiModule {

    @Provides
    @ApplicationScope
    Endpoint provideEndpoint(@ApiEndpoint StringPreference apiEndpoint) {
        return Endpoints.newFixedEndpoint(apiEndpoint.get());
    }

    @Provides
    @ApplicationScope
    MockRestAdapter provideMockRestAdapter(RestAdapter restAdapter, SharedPreferences preferences) {
        MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
        AndroidMockValuePersistence.install(mockRestAdapter, preferences);
        return mockRestAdapter;
    }

    @Provides
    @ApplicationScope
    GalleryService provideGalleryService(RestAdapter restAdapter, MockRestAdapter mockRestAdapter,
                                         @IsMockMode boolean isMockMode, MockGalleryService mockService) {
        if (isMockMode) {
            return mockRestAdapter.create(GalleryService.class, mockService);
        }
        return restAdapter.create(GalleryService.class);
    }

    @Provides
    @ApplicationScope
    ImageService provideImageService(RestAdapter restAdapter, MockRestAdapter mockRestAdapter,
                                         @IsMockMode boolean isMockMode, MockImageService mockService) {
        if (isMockMode) {
            return mockRestAdapter.create(ImageService.class, mockService);
        }
        return restAdapter.create(ImageService.class);
    }
}
