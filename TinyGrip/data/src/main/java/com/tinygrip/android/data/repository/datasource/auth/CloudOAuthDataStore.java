package com.tinygrip.android.data.repository.datasource.auth;

import com.tinygrip.android.data.api.auth.OAuthRestApi;
import com.tinygrip.android.data.cache.auth.OAuthCache;
import com.tinygrip.android.data.entity.user.OAuthEntity;
import javax.inject.Named;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * {@link OAuthDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudOAuthDataStore implements OAuthDataStore {

    private final OAuthRestApi oAuthRestApi;

    private final OAuthCache memoryUserCache;
    private final OAuthCache diskUserCache;

    private final Action1<OAuthEntity> saveToCacheAction = new Action1<OAuthEntity>() {
        @Override
        public void call(OAuthEntity oAuthEntity) {
            if (oAuthEntity != null) {
                CloudOAuthDataStore.this.memoryUserCache.put(oAuthEntity);
                CloudOAuthDataStore.this.diskUserCache.put(oAuthEntity);
            }
        }
    };

    /**
     * Construct a {@link OAuthDataStore} based on connections to the api (Cloud).
     *
     * @param oAuthRestApi The {@link OAuthRestApi} implementation to use.
     * @param memoryOAuthCache A {@link OAuthCache} to cache data retrieved from the api based on memory
     * @param diskOAuthCache A {@link OAuthCache} to cache data retrieved from the api based on disk
     */
    public CloudOAuthDataStore(OAuthRestApi oAuthRestApi,
                              @Named("memoryOAuthCache") OAuthCache memoryOAuthCache,
                              @Named("diskOAuthCache") OAuthCache diskOAuthCache) {
        this.oAuthRestApi = oAuthRestApi;
        this.memoryUserCache = memoryOAuthCache;
        this.diskUserCache = diskOAuthCache;
    }

    @Override
    public Observable<OAuthEntity> performAuth(String userName, String password) {
        return this.oAuthRestApi.authEntity(userName, password)
                               .doOnNext(saveToCacheAction);
    }

    @Override
    public Observable<Boolean> isAuthed() {

        // Obviously we will never be authed correctly if we reached this point
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(false);
                subscriber.onCompleted();
            }
        });
    }
}
