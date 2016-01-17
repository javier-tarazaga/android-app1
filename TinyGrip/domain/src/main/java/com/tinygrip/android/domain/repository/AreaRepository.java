
package com.tinygrip.android.domain.repository;

import com.tinygrip.android.domain.model.DataPage;
import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.domain.model.area.PreviewArea;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link PreviewArea} related data.
 */
public interface AreaRepository {

  /**
   * Get an {@link Observable} which will emit a {@link DataPage<PreviewArea>}.
   */
  Observable<DataPage<PreviewArea>> previewAreas();

  /**
   * Get an {@link Observable} which will emit an {@link Area}.
   */
  Observable<Area> area(String areaHref);
}
