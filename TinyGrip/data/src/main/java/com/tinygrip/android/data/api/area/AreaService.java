package com.tinygrip.android.data.api.area;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.area.AreaEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Area service for interacting with any data related with areas from the network
 */
public interface AreaService {

    @GET("/{previewAreasHref}")
    DataPageEntity<PreviewAreaEntity> previewAreasPageEntitySync(@Path(value = "previewAreasHref", encode = false) String previewAreasHref);

    @GET("/{areaHref}")
    Observable<AreaEntity> area(@Path(value = "areaHref", encode = false) String areaHref);
}
