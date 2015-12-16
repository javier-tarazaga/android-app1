package com.tinygrip.android.data.cache.root;

import com.tinygrip.android.data.entity.RootEntity;
import rx.Observable;

/**
 * An interface representing a root Cache.
 */
public interface RootCache {

    /**
     * Gets an {@link rx.Observable} which will emit a {@link UserEntity}.
     */
    Observable<RootEntity> get();

    /**
     * Puts and element into the cache.
     *
     * @param rootEntity Element to insert in the cache.
     */
    void put(RootEntity rootEntity);

    /**
     * Checks if an element (Root) exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached();
}
