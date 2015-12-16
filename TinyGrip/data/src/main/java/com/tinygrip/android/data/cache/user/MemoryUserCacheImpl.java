package com.tinygrip.android.data.cache.user;

import com.tinygrip.android.data.cache.MemoryCache;
import com.tinygrip.android.data.entity.user.UserEntity;
import com.tinygrip.android.data.exception.user.UserNotFoundException;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;

/**
 * A super simple {@link UserCache} implementation.
 */
@MemoryCache
@Singleton
public class MemoryUserCacheImpl implements UserCache {
    private UserEntity userEntity;

    /**
     * Simple constructor of the class {@link MemoryUserCacheImpl}.
     **/
    @Inject
    public MemoryUserCacheImpl() {
    }

    @Override
    public Observable<UserEntity> get() {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
            @Override
            public void call(Subscriber<? super UserEntity> subscriber) {
                UserEntity userEntity = MemoryUserCacheImpl.this.userEntity;
                if (userEntity != null) {
                    subscriber.onNext(userEntity);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new UserNotFoundException());
                }
            }
        });
    }

    @Override
    public void put(UserEntity userEntity) {
        if (userEntity != null) {
            this.userEntity = userEntity;
        }
    }

    @Override
    public boolean isCached() {
        return this.userEntity != null;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {
        this.userEntity = null;
    }
}
