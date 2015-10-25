package com.tinygrip.android.data.repository.datasource.auth;

import com.tinygrip.android.data.entity.user.OAuthEntity;
import rx.Observable;

/**
 * Created by javier.tarazaga on 22/10/15.
 */
public interface OAuthDataStore {

    /**
     * Get an {@link rx.Observable} which will emit a {@link OAuthEntity} by its userName and password.
     *
     * @param userName The userName to retrieve user data.
     * @param password The password to retrieve user data.
     */
    Observable<OAuthEntity> performAuth(final String userName, final String password);

    Observable<Boolean> isAuthed();
}
