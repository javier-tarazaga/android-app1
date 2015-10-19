package com.tinygrip.android.data.service;

import com.tinygrip.android.data.entity.UserEntity;
import com.tinygrip.android.data.entity.user.UserAuthEntity;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * User service for retrieving the user information and performing login/logout from the application
 */
public interface UserService {

    @GET("/{loginHref}")
    UserEntity getUserDataSync(@Path(value = "loginHref", encode = false) String loginHref);

    @POST("/{authHref}")
    @FormUrlEncoded
    UserAuthEntity performAuthSync(@Path(value = "authHref", encode = false) String authHref,
                                   @Field("grant_type") String grantType,
                                   @Field("username") String username,
                                   @Field("password") String password);
}
