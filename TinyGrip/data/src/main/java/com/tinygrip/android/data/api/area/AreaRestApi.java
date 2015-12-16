
package com.tinygrip.android.data.api.area;

import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.domain.model.PreviewArea;
import rx.Observable;

/**
 * RootRestApi for retrieving data from the network.
 */
public interface AreaRestApi {
  /**
   * Retrieves an {@link Observable} which will emit a {@link DataPageEntity<PreviewArea>}.
   */
  Observable<DataPageEntity<PreviewArea>> previewAreasEntity();
}
