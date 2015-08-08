package com.tinygrip.android.data.service;

import com.tinygrip.android.data.api.ApiConfig;
import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.domain.PreviewArea;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Root service for retrieving the basic application data from the network
 */
public interface RootService {

  @GET(ApiConfig.BASE + "/Root") Observable<RootEntity> rootEntity(@Query("apiKey") String apiKey);

  @GET("/{previewAreasHref}") Observable<DataPageEntity<PreviewArea>> previewAreasPageEntity();
}
