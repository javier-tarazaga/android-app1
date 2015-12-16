
package com.tinygrip.android.data.cache.user;

import com.tinygrip.android.data.entity.user.UserEntity;
import rx.Observable;

/**
 * An interface representing a user Cache.
 */
public interface UserCache {

    /**
     * Gets an {@link rx.Observable} which will emit a {@link UserEntity}.
     */
    Observable<UserEntity> get();

    /**
     * Puts and element into the cache.
     *
     * @param userEntity Element to insert in the cache.
     */
    void put(UserEntity userEntity);

    /**
     * Checks if an element (User) exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached();

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
