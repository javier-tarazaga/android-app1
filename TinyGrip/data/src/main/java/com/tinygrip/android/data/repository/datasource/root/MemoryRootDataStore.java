package com.tinygrip.android.data.repository.datasource.root;

import com.tinygrip.android.data.cache.root.RootCache;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.repository.datasource.user.UserDataStore;
import rx.Observable;

/**
 * {@link UserDataStore} implementation based on memory system data store.
 */
public class MemoryRootDataStore implements RootDataStore {

    private final RootCache rootCache;

    /**
     * Construct a {@link UserDataStore} based file system data store.
     *
     * @param rootCache A {@link RootCache} to cache data retrieved from the api.
     */
    public MemoryRootDataStore(RootCache rootCache) {
        this.rootCache = rootCache;
    }

    @Override
    public Observable<RootEntity> rootEntity(String apiKey) {
        return this.rootCache.get();
    }
}
