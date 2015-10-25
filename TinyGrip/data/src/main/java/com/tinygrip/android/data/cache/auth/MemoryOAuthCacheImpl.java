package com.tinygrip.android.data.cache.auth;

import com.tinygrip.android.data.cache.MemoryCache;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.entity.user.OAuthEntity;
import com.tinygrip.android.data.exception.user.InvalidAuthToken;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;

/**
 * A super simple {@link UserCache} implementation.
 */
@Singleton
@Named("memoryOAuthCache")
public class MemoryOAuthCacheImpl implements OAuthCache {
    private OAuthEntity oAuthEntity;

    /**
     * Simple constructor of the class {@link MemoryOAuthCacheImpl}.
     **/
    @Inject
    public MemoryOAuthCacheImpl() {
    }

    @Override
    public Observable<OAuthEntity> get() {
        return Observable.create(new Observable.OnSubscribe<OAuthEntity>() {
            @Override
            public void call(Subscriber<? super OAuthEntity> subscriber) {
                OAuthEntity oAuthEntity = MemoryOAuthCacheImpl.this.oAuthEntity;
                if (oAuthEntity != null) {
                    subscriber.onNext(oAuthEntity);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new InvalidAuthToken());
                }
            }
        });
    }

    @Override
    public void put(OAuthEntity oAuthEntity) {
        if (oAuthEntity != null) {
            this.oAuthEntity = oAuthEntity;
        }
    }

    @Override
    public boolean isCached() {
        return this.oAuthEntity != null;
    }

    @Override
    public boolean isExpired() {
        // TODO - Check for the expiration field in the entity
        return false;
    }

    @Override
    public void evictAll() {
        this.oAuthEntity = null;
    }
}
