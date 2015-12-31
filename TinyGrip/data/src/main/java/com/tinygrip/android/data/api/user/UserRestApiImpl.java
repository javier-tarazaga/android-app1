
package com.tinygrip.android.data.api.user;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.util.NetworkConnectionHelper;
import com.tinygrip.android.data.entity.user.UserEntity;
import com.tinygrip.android.data.exception.NetworkConnectionException;
import com.tinygrip.android.data.exception.user.UserUnknownException;
import java.net.MalformedURLException;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link UserRestApi} implementation for retrieving data from the network.
 */
public class UserRestApiImpl implements UserRestApi {

    private final Context context;
    private final UserService userService;
    private final SessionData sessionData;

    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     * @param userService {@link UserService}.
     */
    public UserRestApiImpl(Context context, SessionData sessionData, UserService userService) {
        if (context == null || userService == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }

        this.context = context.getApplicationContext();
        this.sessionData = sessionData;
        this.userService = userService;
    }

    private UserEntity getUserDataFromApi() throws MalformedURLException {
        String apiUrl = this.sessionData.getRoot().getAuth().getUser().getHref();

        apiUrl = apiUrl.replace("https://", "").replace("http://", "");

        return userService.getUserDataSync(apiUrl);
    }

    @Override
    public Observable<UserEntity> userEntity() {
        return Observable.create(new Observable.OnSubscribe<UserEntity>() {
            @Override
            public void call(Subscriber<? super UserEntity> subscriber) {

                if (NetworkConnectionHelper.isThereInternetConnection(UserRestApiImpl.this.context)) {
                    try {
                        UserEntity userEntity = getUserDataFromApi();
                        if (userEntity != null) {
                            subscriber.onNext(userEntity);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new UserUnknownException());
                        }
                    } catch (Exception e) {
                        subscriber.onError(new UserUnknownException(e.getCause()));
                    }
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }
}
