
package com.tinygrip.android.data.api.area;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.util.NetworkConnectionHelper;
import com.tinygrip.android.data.entity.DataPageEntity;
import com.tinygrip.android.data.entity.area.AreaEntity;
import com.tinygrip.android.data.entity.area.PreviewAreaEntity;
import com.tinygrip.android.data.exception.NetworkConnectionException;
import com.tinygrip.android.data.exception.NoNetworkConnectionException;
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

    private DataPageEntity<PreviewAreaEntity> getPreviewAreasFromApi() throws MalformedURLException {
        String apiUrl = this.sessionData.getRoot().getPreviewAreas().getHref();

        apiUrl = apiUrl.replace("http://", "");

        return this.areaService.previewAreasPageEntitySync(apiUrl);
    }

    private Observable<AreaEntity> getAreaFromApi(String areaHref) {
        String apiUrl = areaHref.replace("http://", "");

        return this.areaService.area(apiUrl);
    }

    @Override
    public Observable<DataPageEntity<PreviewAreaEntity>> previewAreasEntity() {
        return Observable.create(new Observable.OnSubscribe<DataPageEntity<PreviewAreaEntity>>() {
            @Override
            public void call(Subscriber<? super DataPageEntity<PreviewAreaEntity>> subscriber) {

                if (NetworkConnectionHelper.isThereInternetConnection(AreaRestApiImpl.this.context)) {
                    try {
                        DataPageEntity<PreviewAreaEntity> previewAreas = getPreviewAreasFromApi();
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

    @Override
    public Observable<AreaEntity> area(String areaHref) {
        return this.getAreaFromApi(areaHref);
    }
}

