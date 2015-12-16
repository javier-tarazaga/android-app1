
package com.tinygrip.android.data.api.user;

import com.tinygrip.android.data.entity.user.UserEntity;
import rx.Observable;

/**
 * UserRestApi for retrieving data related to the user from the network.
 */
public interface UserRestApi {

    /**
     * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
     */
    Observable<UserEntity> userEntity();
}
