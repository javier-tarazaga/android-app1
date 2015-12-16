
package com.tinygrip.android.data.repository.datasource.user;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.user.UserRestApi;
import com.tinygrip.android.data.api.user.UserRestApiImpl;
import com.tinygrip.android.data.cache.user.DiskUserCacheImpl;
import com.tinygrip.android.data.cache.user.MemoryUserCacheImpl;
import com.tinygrip.android.data.api.user.UserService;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

    private final Context context;
    private final MemoryUserCacheImpl memoryUserCache;
    private final DiskUserCacheImpl diskUserCache;
    private final UserService userService;
    private final SessionData sessionData;

    @Inject
    public UserDataStoreFactory(Context context, SessionData sessionData, UserService userService,
                                MemoryUserCacheImpl memoryUserCache, DiskUserCacheImpl diskUserCache) {
        if (context == null || sessionData == null || userService == null || memoryUserCache == null
            || diskUserCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.sessionData = sessionData;
        this.userService = userService;
        this.memoryUserCache = memoryUserCache;
        this.diskUserCache = diskUserCache;
    }

    /**
     * Create {@link UserDataStore}
     */
    public UserDataStore create() {
        UserDataStore userDataStore;

        if (!this.memoryUserCache.isExpired() && this.memoryUserCache.isCached()) {
            userDataStore = createMemoryDataStore();
        } else if (!this.diskUserCache.isExpired() && this.diskUserCache.isCached()) {
            userDataStore = createDiskDataStore();
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    /**
     * Simply remove all stored elements in any of the caches
     */
    public void evictAll() {
        this.memoryUserCache.evictAll();
        this.diskUserCache.evictAll();
    }

    /**
     * Create {@link UserDataStore} to retrieve data from memory
     */
    public UserDataStore createMemoryDataStore() {
        return new MemoryUserDataStore(this.memoryUserCache);
    }

    /**
     * Create {@link UserDataStore} to retrieve data from disk
     */
    public UserDataStore createDiskDataStore() {
        return new DiskUserDataStore(this.diskUserCache);
    }

    /**
     * Create {@link UserDataStore} to retrieve data from the Cloud.
     */
    public UserDataStore createCloudDataStore() {
        UserRestApi userRestApi = new UserRestApiImpl(this.context, this.sessionData, this.userService);

        return new CloudUserDataStore(userRestApi, this.memoryUserCache, this.diskUserCache, this.sessionData);
    }
}
