
package com.tinygrip.android.data.api.root;

import com.tinygrip.android.data.entity.RootEntity;
import rx.Observable;

/**
 * RootRestApi for retrieving data from the network.
 */
public interface RootRestApi {

  /**
   * Retrieves an {@link Observable} which will emit a {@link RootEntity}.
   *
   * @param apiKey The apiKey string to be used to identify ourselves in the backend
   */
  Observable<RootEntity> rootEntity(String apiKey);
}
