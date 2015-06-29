package com.tinygrip.android.data.api;


import com.tinygrip.android.data.api.model.response.ImageResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ImageService {
    @GET("/image/{id}")
    Observable<ImageResponse> image(@Path("id") String id);
}
