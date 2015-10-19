package com.tinygrip.android.data.service;

import com.tinygrip.android.data.api.ApiConfig;
import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.domain.PreviewArea;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Root service for retrieving the basic application data from the network
 */
public interface RootService {

    @GET(ApiConfig.BASE + "/Root")
    RootEntity rootEntitySync(@Query("apiKey") String apiKey);

    @GET("/{previewAreasHref}")
    DataPageEntity<PreviewArea> previewAreasPageEntitySync();
}
