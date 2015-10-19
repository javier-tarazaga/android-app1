
package com.tinygrip.android.domain.repository;

import com.tinygrip.android.domain.User;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface UserRepository {

    /**
     * Get an {@link rx.Observable} which will emit a {@link User}.
     *
     * @param userName The user name used to perform the login with
     * @param password The user password used to perform the login with.
     */
    Observable<User> user(final String userName, final String password);


    /**
     * Get an {@link rx.Observable} which will emit a {@link User}.
     */
    Observable<User> user();
}
