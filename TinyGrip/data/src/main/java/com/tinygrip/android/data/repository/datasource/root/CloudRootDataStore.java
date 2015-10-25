
package com.tinygrip.android.data.repository.datasource.root;

import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.root.RootRestApi;
import com.tinygrip.android.data.cache.root.RootCache;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.api.root.RootService;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link RootDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudRootDataStore implements RootDataStore {

    private final RootRestApi rootRestApi;
    private final RootCache rootCache;
    private final SessionData sessionData;

    private final Action1<RootEntity> saveToSessionDataAction = new Action1<RootEntity>() {
        @Override
        public void call(RootEntity rootEntity) {
            if (rootEntity != null) {
                CloudRootDataStore.this.sessionData.setRoot(rootEntity);
            }
        }
    };

    private final Action1<RootEntity> saveToRootCacheAction = new Action1<RootEntity>() {
        @Override
        public void call(RootEntity rootEntity) {
            if (rootEntity != null) {
                CloudRootDataStore.this.rootCache.put(rootEntity);
            }
        }
    };

    /**
     * Construct a {@link RootDataStore} based on connections to the api (Cloud).
     *  @param rootRestApi The {@link RootService} implementation to use.
     * @param rootCache The {@link RootCache} to cache retrieved data from the api
     * @param sessionData The {@link SessionData} to cache retrieved data from the api.
     */
    public CloudRootDataStore(RootRestApi rootRestApi, RootCache rootCache, SessionData sessionData) {
        this.rootRestApi = rootRestApi;
        this.rootCache = rootCache;
        this.sessionData = sessionData;
    }

    @Override
    public Observable<RootEntity> rootEntity(String apiKey) {
        // Make sure to save the rootEntity in the root cache for later access
        // TODO - Store it in the sessionData until figuring out how to use the cached values from other data
        // factories
        return this.rootRestApi.rootEntity(apiKey)
                               .doOnNext(saveToRootCacheAction)
                               .doOnNext(saveToSessionDataAction);
    }
}
