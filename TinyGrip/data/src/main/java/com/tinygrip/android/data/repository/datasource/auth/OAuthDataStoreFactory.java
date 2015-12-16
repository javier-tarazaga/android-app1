package com.tinygrip.android.data.repository.datasource.auth;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.ApiRequestInterceptor;
import com.tinygrip.android.data.api.auth.OAuthRestApi;
import com.tinygrip.android.data.api.auth.OAuthRestApiImpl;
import com.tinygrip.android.data.api.auth.OAuthService;
import com.tinygrip.android.data.cache.auth.OAuthCache;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by javier.tarazaga on 22/10/15.
 */
@Singleton
public class OAuthDataStoreFactory {

    private final Context context;
    private final ApiRequestInterceptor requestInterceptor;
    private final SessionData sessionData;
    private final OAuthService oAuthService;

    private final OAuthCache memoryOAuthCache;
    private final OAuthCache diskOAuthCache;

    @Inject
    public OAuthDataStoreFactory(Context context,
                                 ApiRequestInterceptor requestInterceptor,
                                 SessionData sessionData,
                                 OAuthService oAuthService,
                                 @Named("memoryOAuthCache") OAuthCache memoryOAuthCache,
                                 @Named("diskOAuthCache") OAuthCache diskOAuthCache) {

        if (context == null || sessionData == null || oAuthService == null || memoryOAuthCache == null
            || diskOAuthCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }

        this.context = context;
        this.requestInterceptor = requestInterceptor;
        this.sessionData = sessionData;
        this.oAuthService = oAuthService;
        this.memoryOAuthCache = memoryOAuthCache;
        this.diskOAuthCache = diskOAuthCache;
    }

    /**
     * Create {@link OAuthDataStore}
     */
    public OAuthDataStore create() {
        OAuthDataStore oAuthDataStore;

        if (!this.memoryOAuthCache.isExpired() && this.memoryOAuthCache.isCached()) {
            oAuthDataStore = createMemoryDataStore();
        } else if (!this.diskOAuthCache.isExpired() && this.diskOAuthCache.isCached()) {
            oAuthDataStore = createDiskDataStore();
        } else {
            oAuthDataStore = createCloudDataStore();
        }

        return oAuthDataStore;
    }

    /**
     * Simply remove all stored elements in any of the caches
     */
    public void evictAll() {
        this.memoryOAuthCache.evictAll();
        this.diskOAuthCache.evictAll();
    }

    /**
     * Create {@link OAuthDataStore} to retrieve data from memory
     */
    private OAuthDataStore createMemoryDataStore() {
        return new MemoryOAuthDataStore(this.memoryOAuthCache);
    }

    /**
     * Create {@link OAuthDataStore} to retrieve data from disk
     */
    private OAuthDataStore createDiskDataStore() {
        return new DiskOAuthDataStore(this.diskOAuthCache);
    }

    /**
     * Create {@link OAuthDataStore} to retrieve data from the api
     */
    private OAuthDataStore createCloudDataStore() {
        OAuthRestApi oAuthRestApi = new OAuthRestApiImpl(this.context, this.requestInterceptor, this.sessionData, this.oAuthService);

        return new CloudOAuthDataStore(oAuthRestApi, this.memoryOAuthCache, this.diskOAuthCache);
    }
}
