package com.tinygrip.android.data.cache.root;

import com.tinygrip.android.data.cache.MemoryCache;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.exception.RootNotFoundException;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;

/**
 * A super simple {@link UserCache} implementation.
 */
@MemoryCache
@Singleton
public class MemoryRootCacheImpl implements RootCache {
    private RootEntity rootEntity;

    /**
     * Simple constructor of the class {@link MemoryRootCacheImpl}.
     **/
    @Inject
    public MemoryRootCacheImpl() {
    }

    @Override
    public Observable<RootEntity> get() {
        return Observable.create(new Observable.OnSubscribe<RootEntity>() {
            @Override
            public void call(Subscriber<? super RootEntity> subscriber) {
                RootEntity rootEntity = MemoryRootCacheImpl.this.rootEntity;
                if (rootEntity != null) {
                    subscriber.onNext(rootEntity);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new RootNotFoundException());
                }
            }
        });
    }

    @Override
    public void put(RootEntity rootEntity) {
        if (rootEntity != null) {
            this.rootEntity = rootEntity;
        }
    }

    @Override
    public boolean isCached() {
        return this.rootEntity != null;
    }
}
