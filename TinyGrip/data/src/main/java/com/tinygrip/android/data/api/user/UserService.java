package com.tinygrip.android.data.api.user;

import com.tinygrip.android.data.entity.user.UserEntity;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * User service for retrieving the user information and performing login/logout from the application
 */
public interface UserService {

    @GET("/{loginHref}")
    UserEntity getUserDataSync(@Path(value = "loginHref", encode = false) String loginHref);
}
