package com.tinygrip.android.data.api;

import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit.RequestInterceptor;

@Singleton
public final class ApiRequestInterceptor implements RequestInterceptor {

    private String deviceAuthToken;
    private String userAuthToken;

    @Inject
    public ApiRequestInterceptor() {
    }

    public void setDeviceAuthToken(String deviceAuthToken) {
        this.deviceAuthToken = deviceAuthToken;
    }

    public void setUserAuthToken(String userAuthToken) {
        this.userAuthToken = userAuthToken;
    }

    @Override
    public void intercept(RequestFacade request) {

        if (deviceAuthToken != null) {
            request.addHeader("Cookie", deviceAuthToken + (userAuthToken != null ? ";" + userAuthToken : ""));
            request.addHeader("Accept", "application/json");
            request.addHeader("Content-Type", "application/json");
        }
    }
}
