
package com.tinygrip.android.data.api.area;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.util.NetworkConnectionHelper;
import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.exception.NetworkConnectionException;
import com.tinygrip.android.data.exception.NoNetworkConnectionException;
import com.tinygrip.android.domain.model.PreviewArea;
import java.net.MalformedURLException;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link AreaRestApiImpl} implementation for retrieving data from the network.
 */
public class AreaRestApiImpl implements AreaRestApi {

    private final Context context;
    private final AreaService areaService;
    private final SessionData sessionData;

    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     * @param areaService {@link AreaService}.
     */
    public AreaRestApiImpl(Context context, SessionData sessionData, AreaService areaService) {
        if (context == null || areaService == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.sessionData = sessionData;
        this.areaService = areaService;
    }
    private DataPageEntity<PreviewArea> getPreviewAreasFromApi() throws MalformedURLException {
        String apiUrl = this.sessionData.getRoot().getUser().getHref();

        return this.areaService.previewAreasPageEntitySync(apiUrl);
    }

    @Override
    public Observable<DataPageEntity<PreviewArea>> previewAreasEntity() {
        return Observable.create(new Observable.OnSubscribe<DataPageEntity<PreviewArea>>() {
            @Override
            public void call(Subscriber<? super DataPageEntity<PreviewArea>> subscriber) {

                if (NetworkConnectionHelper.isThereInternetConnection(AreaRestApiImpl.this.context)) {
                    try {
                        DataPageEntity<PreviewArea> previewAreas = getPreviewAreasFromApi();
                        if (previewAreas != null) {
                            subscriber.onNext(previewAreas);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    subscriber.onError(new NoNetworkConnectionException());
                }
            }
        });
    }
}

