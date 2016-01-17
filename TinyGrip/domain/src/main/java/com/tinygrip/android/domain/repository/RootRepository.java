
package com.tinygrip.android.domain.repository;

import com.tinygrip.android.domain.model.Root;
import com.tinygrip.android.domain.model.user.User;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface RootRepository {

  /**
   * Get an {@link Observable} which will emit a {@link Root}.
   */
  Observable<Void> root(String apiKey);
}
