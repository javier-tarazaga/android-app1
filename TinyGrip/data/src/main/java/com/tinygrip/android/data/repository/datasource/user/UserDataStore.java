
package com.tinygrip.android.data.repository.datasource.user;

import com.tinygrip.android.data.entity.user.UserEntity;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface UserDataStore {

    /**
     * Get an {@link rx.Observable} which will emit a {@link UserEntity}
     */
    Observable<UserEntity> userEntity();
}
