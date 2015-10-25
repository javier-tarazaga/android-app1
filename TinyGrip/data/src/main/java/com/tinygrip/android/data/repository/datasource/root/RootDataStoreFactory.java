
package com.tinygrip.android.data.repository.datasource.root;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.root.RootRestApi;
import com.tinygrip.android.data.api.root.RootRestApiImpl;
import com.tinygrip.android.data.cache.root.RootCache;
import com.tinygrip.android.data.api.root.RootService;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link RootDataStore}.
 */
@Singleton
public class RootDataStoreFactory {

    private final RootService rootService;
    private final Context context;
    private final RootCache rootCache;
    private final SessionData sessionData;

    @Inject
    public RootDataStoreFactory(Context context, RootService rootService, RootCache rootCache, SessionData sessionData) {
        if (context == null || rootService == null || rootCache == null || sessionData == null)  {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }

        this.context = context;
        this.rootCache = rootCache;
        this.rootService = rootService;
        this.sessionData = sessionData;
    }

    /**
     * Create {@link RootDataStore} depending on cached values.
     */
    public RootDataStore create() {
        RootDataStore rootDataStore;

        if (this.rootCache.isCached()) {
            rootDataStore = new MemoryRootDataStore(this.rootCache);
        } else {
            rootDataStore = createCloudDataStore();
        }

        return rootDataStore;
    }

    /**
     * Create {@link RootDataStore} to retrieve data from the Cloud.
     */
    public RootDataStore createCloudDataStore() {
        RootRestApi rootRestApi = new RootRestApiImpl(this.context, this.rootService);

        return new CloudRootDataStore(rootRestApi, this.rootCache, this.sessionData);
    }
}
