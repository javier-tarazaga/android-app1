
package com.tinygrip.android.data.api.user;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.RootRestApi;
import com.tinygrip.android.data.api.util.NetworkConnectionHelper;
import com.tinygrip.android.data.entity.UserEntity;
import com.tinygrip.android.data.entity.user.UserAuthEntity;
import com.tinygrip.android.data.exception.NetworkConnectionException;
import com.tinygrip.android.data.service.UserService;
import java.net.MalformedURLException;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link RootRestApi} implementation for retrieving data from the network.
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

    private UserAuthEntity getUserTokenFromApi(String userName, String password) throws MalformedURLException {
        String apiUrl = this.sessionData.getRoot().getAccount().getAuthenticate().getHref();

        apiUrl = apiUrl.replace("https://", "");

        return userService.performAuthSync(apiUrl, "password", userName, password);
    }

    private UserEntity getUserDataFromApi() throws MalformedURLException {
        String apiUrl = this.sessionData.getRoot().getAccount().getData().getHref();

        apiUrl = apiUrl.replace("https://", "");

        return userService.getUserDataSync(apiUrl);
    }

    @Override
    public Observable<UserAuthEntity> userAuthEntity(final String userName, final String password) {
        return Observable.create(new Observable.OnSubscribe<UserAuthEntity>() {
            @Override
            public void call(Subscriber<? super UserAuthEntity> subscriber) {

                if (NetworkConnectionHelper.isThereInternetConnection(UserRestApiImpl.this.context)) {
                    try {
                        UserAuthEntity userAuthEntity = getUserTokenFromApi(userName, password);
                        if (userAuthEntity != null) {
                            subscriber.onNext(userAuthEntity);
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
