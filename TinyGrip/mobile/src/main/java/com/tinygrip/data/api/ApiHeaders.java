package com.tinygrip.data.api;

import com.tinygrip.ui.ApplicationScope;

import javax.inject.Inject;

import retrofit.RequestInterceptor;

@ApplicationScope
public final class ApiHeaders implements RequestInterceptor {
    private static final String AUTHORIZATION_PREFIX = "Client-ID";

    private final String authorizationValue;

    @Inject
    public ApiHeaders(@ClientId String clientId) {
        authorizationValue = AUTHORIZATION_PREFIX + " " + clientId;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Authorization", authorizationValue);
    }
}
