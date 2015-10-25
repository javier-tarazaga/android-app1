
package com.tinygrip.android.data.api.auth;

import com.tinygrip.android.data.entity.user.OAuthEntity;
import rx.Observable;

/**
 * UserRestApi for retrieving data related to the user from the network.
 */
public interface OAuthRestApi {

    /**
     * Retrieves an {@link Observable} which will emit a {@link OAuthEntity}.
     *
     * @param userName The user name used to perform the authentication with
     * @param password The user password used to perform the authentication with
     */
    Observable<OAuthEntity> authEntity(final String userName, final String password);
}
