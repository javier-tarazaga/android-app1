
package com.tinygrip.android.data.repository.datasource.auth;

import com.tinygrip.android.data.cache.auth.OAuthCache;
import com.tinygrip.android.data.entity.user.OAuthEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link OAuthDataStore} implementation based on file system data store.
 */
public class MemoryOAuthDataStore implements OAuthDataStore {

    private final OAuthCache oAuthCache;

    /**
     * Construct a {@link OAuthDataStore} based file system data store.
     *
     * @param oAuthCache A {@link OAuthCache} to cache data retrieved from the api.
     */
    public MemoryOAuthDataStore(OAuthCache oAuthCache) {
        this.oAuthCache = oAuthCache;
    }

    @Override
    public Observable<OAuthEntity> performAuth(String userName, String password) {
        return this.oAuthCache.get();
    }

    @Override
    public Observable<Boolean> isAuthed() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(!MemoryOAuthDataStore.this.oAuthCache.isExpired()
                                      && MemoryOAuthDataStore.this.oAuthCache.isCached());
                subscriber.onCompleted();
            }
        });
    }
}
