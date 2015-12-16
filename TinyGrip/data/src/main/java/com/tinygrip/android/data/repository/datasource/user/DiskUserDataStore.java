
package com.tinygrip.android.data.repository.datasource.user;

import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.entity.user.UserEntity;
import rx.Observable;

/**
 * {@link UserDataStore} implementation based on file system data store.
 */
public class DiskUserDataStore implements UserDataStore {

  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  public DiskUserDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override
  public Observable<UserEntity> userEntity() {
    return this.userCache.get();
  }
}
