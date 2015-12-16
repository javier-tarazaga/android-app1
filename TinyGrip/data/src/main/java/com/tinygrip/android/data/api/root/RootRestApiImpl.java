
package com.tinygrip.android.data.api.root;

import android.content.Context;
import com.tinygrip.android.data.api.util.NetworkConnectionHelper;
import com.tinygrip.android.data.entity.RootEntity;
import com.tinygrip.android.data.exception.NetworkConnectionException;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link RootRestApi} implementation for retrieving data from the network.
 */
public class RootRestApiImpl implements RootRestApi {

    private final Context context;
    private final RootService rootService;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param rootService {@link RootService}.
     */
    public RootRestApiImpl(Context context, RootService rootService) {
        if (context == null || rootService == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.rootService = rootService;
    }

    private RootEntity getRootFromApi(String apiKey) {
        return this.rootService.rootEntitySync(apiKey);
    }

    @Override
    public Observable<RootEntity> rootEntity(final String apiKey) {
        return Observable.create(new Observable.OnSubscribe<RootEntity>() {
            @Override
            public void call(Subscriber<? super RootEntity> subscriber) {

                if (NetworkConnectionHelper.isThereInternetConnection(RootRestApiImpl.this.context)) {
                    try {
                        RootEntity rootEntity = getRootFromApi(apiKey);
                        if (rootEntity != null) {
                            subscriber.onNext(rootEntity);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }
}

