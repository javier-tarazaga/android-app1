package com.tinygrip.android.data.api.area;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Area service for interacting with any data related with areas from the network
 */
public interface AreaService {

    @GET("/{previewAreasHref}")
    DataPageEntity<PreviewAreaEntity> previewAreasPageEntitySync(@Path(value = "previewAreasHref", encode = false) String previewAreasHref);
}
