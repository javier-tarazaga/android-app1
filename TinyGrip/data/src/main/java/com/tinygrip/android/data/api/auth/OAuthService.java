package com.tinygrip.android.data.api.auth;

import com.tinygrip.android.data.entity.user.OAuthEntity;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * OAuth service for authenticating the user against the backend
 */
public interface OAuthService {

    @POST("/{authHref}")
    @FormUrlEncoded
    OAuthEntity performAuthSync(@Path(value = "authHref", encode = false) String authHref,
                                @Field("grant_type") String grantType,
                                @Field("username") String username,
                                @Field("password") String password);
}