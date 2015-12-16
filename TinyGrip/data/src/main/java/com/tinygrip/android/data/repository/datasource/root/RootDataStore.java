
package com.tinygrip.android.data.repository.datasource.root;

import com.tinygrip.android.data.entity.RootEntity;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface RootDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link RootEntity}.
   */
  Observable<RootEntity> rootEntity(String apiKey);
}
