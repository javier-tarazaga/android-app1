
package com.tinygrip.android.data.api.user;

import com.tinygrip.android.data.entity.UserEntity;
import com.tinygrip.android.data.entity.user.UserAuthEntity;
import rx.Observable;

/**
 * UserRestApi for retrieving data related to the user from the network.
 */
public interface UserRestApi {

    /**
     * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
     *
     * @param userName The user name used to perform the login
     * @param password The user password used to perform the login
     */
    Observable<UserAuthEntity> userAuthEntity(final String userName, final String password);

    Observable<UserEntity> userEntity();
}
