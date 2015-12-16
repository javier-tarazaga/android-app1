
package com.tinygrip.android.data.repository.datasource.user;

import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.user.UserRestApi;
import com.tinygrip.android.data.cache.DiskCache;
import com.tinygrip.android.data.cache.MemoryCache;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.entity.user.UserEntity;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements UserDataStore {

    private final UserRestApi userRestApi;
    private final UserCache memoryUserCache;
    private final UserCache diskUserCache;
    private final SessionData sessionData;

    private final Action1<UserEntity> saveToSessionDataAction = new Action1<UserEntity>() {
        @Override
        public void call(UserEntity userEntity) {
            if (userEntity != null) {
                CloudUserDataStore.this.sessionData.setUser(userEntity);
            }
        }
    };

    private final Action1<UserEntity> saveToCacheAction = new Action1<UserEntity>() {
        @Override
        public void call(UserEntity userEntity) {
            if (userEntity != null) {
                CloudUserDataStore.this.memoryUserCache.put(userEntity);
                CloudUserDataStore.this.diskUserCache.put(userEntity);
            }
        }
    };

    /**
     * Construct a {@link UserDataStore} based on connections to the api (Cloud).
     *
     * @param userRestApi The {@link UserRestApi} implementation to use.
     * @param memoryUserCache A {@link UserCache} to cache data retrieved from the api based on memory
     * @param diskUserCache A {@link UserCache} to cache data retrieved from the api based on disk
     */
    public CloudUserDataStore(UserRestApi userRestApi,
                              @MemoryCache UserCache memoryUserCache,
                              @DiskCache UserCache diskUserCache,
                              SessionData sessionData) {
        this.userRestApi = userRestApi;
        this.memoryUserCache = memoryUserCache;
        this.diskUserCache = diskUserCache;
        this.sessionData = sessionData;
    }

    @Override
    public Observable<UserEntity> userEntity() {
        return this.userRestApi.userEntity()
                               .doOnNext(saveToCacheAction)
                               .doOnNext(saveToSessionDataAction);
    }
}
