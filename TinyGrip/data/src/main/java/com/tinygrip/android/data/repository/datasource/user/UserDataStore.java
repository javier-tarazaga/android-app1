
package com.tinygrip.android.data.repository.datasource.user;

import com.tinygrip.android.data.entity.UserEntity;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface UserDataStore {

    /**
     * Get an {@link rx.Observable} which will emit a {@link UserEntity} by its userName and password.
     *
     * @param userName The userName to retrieve user data.
     * @param password The password to retrieve user data.
     */
    Observable<UserEntity> userEntityLogin(final String userName, final String password);

    /**
     * Get an {@link rx.Observable} which will emit a {@link UserEntity}
     */
    Observable<UserEntity> userEntity();
}
