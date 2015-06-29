package com.tinygrip.android.data;

import android.app.Application;
import android.net.Uri;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.tinygrip.android.data.api.ReleaseApiModule;
import com.tinygrip.android.ui.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(includes = { DataModule.class, ReleaseApiModule.class })
public final class ReleaseDataModule {

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(Application app) {
        return DataModule.createOkHttpClient(app);
    }

    @Provides
    @ApplicationScope
    Picasso providePicasso(Application app, OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                        Timber.e(e, "Failed to load image: %s", uri);
                    }
                })
                .build();
    }
}
