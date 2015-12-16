package com.tinygrip.android.data.api.area;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.domain.model.PreviewArea;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Area service for interacting with any data related with areas from the network
 */
public interface AreaService {

    @GET("/{previewAreasHref}")
    DataPageEntity<PreviewArea> previewAreasPageEntitySync(@Path(value = "previewAreasHref", encode = false) String previewAreasHref);
}
