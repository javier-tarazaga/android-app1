
package com.tinygrip.android.domain.repository;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link com.tinygrip.android.domain.PreviewArea} related data.
 */
public interface AreaRepository {

  /**
   * Get an {@link Observable} which will emit a {@link com.tinygrip.android.domain.PreviewArea}.
   */
  Observable<com.tinygrip.android.domain.PreviewArea> previewAreas();
}
