
package com.tinygrip.android.domain.repository;

import com.tinygrip.android.domain.model.user.User;
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
     * Get an {@link rx.Observable} which will call onComplete when the logout operation has been
     * terminated correctly.
     */
    Observable<Object> userLogout();


    /**
     * Get an {@link rx.Observable} which will emit a {@link User}.
     */
    Observable<User> user();

    /**
     * Get an {@link rx.Observable} which will emit a {@link Boolean} representing if the has a valid auth state
     * or not.
     */
    Observable<Boolean> isValidUser();

    /**
     * Get an {@link rx.Observable} which will emit a {@link User}.
     *
     * @param email The user email address to associate with the user
     * @param password The user password to associate with the user
     * @param confirmPassword The confirmation password (which we assume has to be the same as the password) in orer to
     * make sure the user does not mis-spell their password while typing it the first time.
     */
    Observable<User> registerUser(final String email, final String password, final String confirmPassword);
}
