
package com.tinygrip.android.data.cache.auth;

import com.tinygrip.android.data.entity.user.OAuthEntity;
import rx.Observable;

/**
 * An interface representing a user Cache.
 */
public interface OAuthCache {

    /**
     * Gets an {@link Observable} which will emit a {@link OAuthEntity}.
     */
    Observable<OAuthEntity> get();

    /**
     * Puts and element into the cache.
     *
     * @param oAuthEntity Element to insert in the cache.
     */
    void put(OAuthEntity oAuthEntity);

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
