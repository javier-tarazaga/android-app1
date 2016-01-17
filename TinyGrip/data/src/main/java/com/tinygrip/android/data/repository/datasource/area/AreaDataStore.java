
package com.tinygrip.android.data.repository.datasource.area;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.area.AreaEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import com.tinygrip.android.domain.model.area.PreviewArea;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface AreaDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link DataPageEntity<PreviewArea>}.
   */
  Observable<DataPageEntity<PreviewAreaEntity>> previewAreas();

  /**
   * Get an {@link Observable} which will emit an {@link AreaEntity}.
   */
  Observable<AreaEntity> area(String areaHref);
}
