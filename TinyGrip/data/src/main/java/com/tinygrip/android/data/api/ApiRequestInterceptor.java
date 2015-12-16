package com.tinygrip.android.data.api;

import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit.RequestInterceptor;

@Singleton
public final class ApiRequestInterceptor implements RequestInterceptor {

    private String userAuthToken;

    @Inject
    public ApiRequestInterceptor() {
    }

    public void setUserAuthToken(String userAuthToken) {
        this.userAuthToken = userAuthToken;
    }

    @Override
    public void intercept(RequestFacade request) {

        if (this.userAuthToken != null) {
            request.addHeader("Authorization", "bearer " + userAuthToken);
        }

        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");
    }
}
