
package com.tinygrip.android.data.repository.datasource.user;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.user.UserRestApi;
import com.tinygrip.android.data.api.user.UserRestApiImpl;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.service.UserService;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

    private final Context context;
    private final UserCache userCache;
    private final UserService userService;
    private final SessionData sessionData;

    @Inject
    public UserDataStoreFactory(Context context, SessionData sessionData, UserService userService, UserCache userCache) {
        if (context == null || sessionData == null || userCache == null || userService == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.sessionData = sessionData;
        this.userService = userService;
        this.userCache = userCache;
    }

    /**
     * Create {@link UserDataStore}
     */
    public UserDataStore create() {
        UserDataStore userDataStore;

        if (!this.userCache.isExpired() && this.userCache.isCached()) {
            userDataStore = new MemoryUserDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    /**
     * Create {@link UserDataStore} to retrieve data from the Cloud.
     */
    public UserDataStore createCloudDataStore() {
        UserRestApi userRestApi = new UserRestApiImpl(this.context, this.sessionData, this.userService);

        return new CloudUserDataStore(userRestApi, this.userCache);
    }
}
