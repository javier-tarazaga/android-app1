package com.tinygrip.android.data.api.model;


import com.tinygrip.android.data.api.ImageService;
import com.tinygrip.android.data.api.ServerDatabase;
import com.tinygrip.android.data.api.model.response.ImageResponse;

import javax.inject.Inject;

import retrofit.http.Path;
import rx.Observable;

public class MockImageService implements ImageService {

    private final ServerDatabase serverDatabase;

    @Inject
    MockImageService(ServerDatabase serverDatabase) {
        this.serverDatabase = serverDatabase;
    }

    @Override
    public Observable<ImageResponse> image(@Path("id") String id) {
        return Observable.just(serverDatabase.getImageForId(id));
    }
}
