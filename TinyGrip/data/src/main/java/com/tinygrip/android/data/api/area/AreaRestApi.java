
package com.tinygrip.android.data.api.area;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.area.AreaEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import com.tinygrip.android.domain.model.area.PreviewArea;
import rx.Observable;

/**
 * RootRestApi for retrieving data from the network.
 */
public interface AreaRestApi {
  /**
   * Retrieves an {@link Observable} which will emit a {@link DataPageEntity<PreviewArea>}.
   */
  Observable<DataPageEntity<PreviewAreaEntity>> previewAreasEntity();

  /**
   * Get an {@link Observable} which will emit an {@link AreaEntity}.
   */
  Observable<AreaEntity> area(String areaHref);
}
