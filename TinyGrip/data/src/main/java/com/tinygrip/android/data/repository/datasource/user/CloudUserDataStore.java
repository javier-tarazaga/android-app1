
package com.tinygrip.android.data.repository.datasource.user;

import com.tinygrip.android.data.api.RootRestApi;
import com.tinygrip.android.data.api.user.UserRestApi;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.entity.UserEntity;
import com.tinygrip.android.data.entity.user.UserAuthEntity;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudUserDataStore implements UserDataStore {

    private final UserRestApi userRestApi;
    private final UserCache userCache;

    private final Action1<UserEntity> saveToCacheAction = new Action1<UserEntity>() {
        @Override
        public void call(UserEntity userEntity) {
            if (userEntity != null) {
                CloudUserDataStore.this.userCache.put(userEntity);
            }
        }
    };

    /**
     * Construct a {@link UserDataStore} based on connections to the api (Cloud).
     *
     * @param userRestApi The {@link RootRestApi} implementation to use.
     * @param userCache A {@link UserCache} to cache data retrieved from the api.
     */
    public CloudUserDataStore(UserRestApi userRestApi, UserCache userCache) {
        this.userRestApi = userRestApi;
        this.userCache = userCache;
    }

    @Override
    public Observable<UserEntity> userEntityLogin(String userName, String password) {
        return this.userRestApi.userAuthEntity(userName, password)
                               .flatMap(new Func1<UserAuthEntity, Observable<UserEntity>>() {
                                   @Override
                                   public Observable<UserEntity> call(UserAuthEntity userAuthEntity) {
                                       return CloudUserDataStore.this.userRestApi.userEntity();
                                   }
                               })
                               .doOnNext(saveToCacheAction);
    }

    @Override
    public Observable<UserEntity> userEntity() {
        return this.userRestApi.userEntity()
                               .doOnNext(saveToCacheAction);
    }
}
