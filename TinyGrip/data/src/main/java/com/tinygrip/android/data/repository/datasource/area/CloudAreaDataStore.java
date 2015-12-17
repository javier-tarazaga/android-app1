
package com.tinygrip.android.data.repository.datasource.area;

import com.tinygrip.android.data.api.area.AreaRestApi;
import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.domain.model.PreviewArea;
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
    public Observable<DataPageEntity<PreviewArea>> previewAreas() {
        return this.areaRestApi.previewAreasEntity();
    }
}
