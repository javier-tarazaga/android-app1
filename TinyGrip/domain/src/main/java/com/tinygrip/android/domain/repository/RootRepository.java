
package com.tinygrip.android.domain.repository;

import com.tinygrip.android.domain.Root;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link com.tinygrip.android.domain.User} related data.
 */
public interface RootRepository {

  /**
   * Get an {@link Observable} which will emit a {@link com.tinygrip.android.domain.Root}.
   */
  Observable<Root> root(String apiKey);
}
