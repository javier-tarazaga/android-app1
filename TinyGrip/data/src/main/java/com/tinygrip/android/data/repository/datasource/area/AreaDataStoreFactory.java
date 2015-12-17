
package com.tinygrip.android.data.repository.datasource.area;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.area.AreaRestApi;
import com.tinygrip.android.data.api.area.AreaRestApiImpl;
import com.tinygrip.android.data.api.area.AreaService;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link AreaDataStore}.
 */
@Singleton
public class AreaDataStoreFactory {

    private final AreaService areaService;
    private final Context context;
    private final SessionData sessionData;

    @Inject
    public AreaDataStoreFactory(Context context, AreaService areaService, SessionData sessionData) {
        if (context == null || areaService == null || sessionData == null)  {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }

        this.context = context;
        this.areaService = areaService;
        this.sessionData = sessionData;
    }

    /**
     * Create {@link AreaDataStore}
     */
    public AreaDataStore create() {
        return createCloudDataStore();
    }

    /**
     * Create {@link AreaDataStore} to retrieve data from the Cloud.
     */
    public AreaDataStore createCloudDataStore() {
        AreaRestApi areaRestApi = new AreaRestApiImpl(this.context, this.sessionData, this.areaService);

        return new CloudAreaDataStore(areaRestApi);
    }
}
