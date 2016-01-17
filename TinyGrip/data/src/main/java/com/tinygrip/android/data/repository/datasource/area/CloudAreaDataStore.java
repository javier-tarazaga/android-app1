
package com.tinygrip.android.data.repository.datasource.area;

import com.tinygrip.android.data.api.area.AreaRestApi;
import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.area.AreaEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import rx.Observable;

/**
 * {@link AreaDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudAreaDataStore implements AreaDataStore {

    private final AreaRestApi areaRestApi;

    /**
     * Construct a {@link AreaDataStore} based on connections to the api (Cloud).
     *
     *  @param areaRestApi The {@link AreaRestApi} implementation to use.
     */
    public CloudAreaDataStore(AreaRestApi areaRestApi) {
        this.areaRestApi = areaRestApi;
    }

    @Override
    public Observable<DataPageEntity<PreviewAreaEntity>> previewAreas() {
        return this.areaRestApi.previewAreasEntity();
    }

    @Override
    public Observable<AreaEntity> area(String areaHref) {
        return this.areaRestApi.area(areaHref);
    }
}
