
package com.tinygrip.android.data.api.auth;

import android.content.Context;
import com.tinygrip.android.data.SessionData;
import com.tinygrip.android.data.api.ApiRequestInterceptor;
import com.tinygrip.android.data.api.user.UserService;
import com.tinygrip.android.data.api.util.NetworkConnectionHelper;
import com.tinygrip.android.data.entity.user.OAuthEntity;
import com.tinygrip.android.data.exception.NetworkConnectionException;
import java.net.MalformedURLException;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link OAuthRestApi} implementation for performing authentications from the network.
 */
public class OAuthRestApiImpl implements OAuthRestApi {

    private final Context context;
    private final OAuthService oAuthService;
    private final SessionData sessionData;
    private final ApiRequestInterceptor apiRequestInterceptor;

    /**
     * Constructor of the class
     *
     * @param context {@link Context}.
     * @param oAuthService {@link UserService}.
     */
    public OAuthRestApiImpl(Context context, ApiRequestInterceptor apiRequestInterceptor, SessionData sessionData, OAuthService oAuthService) {
        if (context == null || oAuthService == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }

        this.context = context.getApplicationContext();
        this.apiRequestInterceptor = apiRequestInterceptor;
        this.sessionData = sessionData;
        this.oAuthService = oAuthService;
    }

    private OAuthEntity performOAuth(String userName, String password) throws MalformedURLException {
        String apiUrl = this.sessionData.getRoot().getAuth().getToken().getHref();

        apiUrl = apiUrl.replace("https://", "").replace("http://", "");

        return oAuthService.performAuthSync(apiUrl, "password", userName, password);
    }

    @Override
    public Observable<OAuthEntity> authEntity(final String userName, final String password) {
        return Observable.create(new Observable.OnSubscribe<OAuthEntity>() {
            @Override
            public void call(Subscriber<? super OAuthEntity> subscriber) {

                if (NetworkConnectionHelper.isThereInternetConnection(OAuthRestApiImpl.this.context)) {
                    try {
                        OAuthEntity oAuthEntity = performOAuth(userName, password);
                        if (oAuthEntity != null) {

                            OAuthRestApiImpl.this.apiRequestInterceptor.setUserAuthToken(oAuthEntity.getAccessToken());

                            subscriber.onNext(oAuthEntity);
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
