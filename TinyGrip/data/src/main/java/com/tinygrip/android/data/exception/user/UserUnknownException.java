package com.tinygrip.android.data.exception.user;

/**
 * Exception which represents and un-expected error while trying to retrieve a user.
 */
public class UserUnknownException extends Exception {

    public UserUnknownException() {
        super();
    }

    public UserUnknownException(final String message) {
        super(message);
    }

    public UserUnknownException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserUnknownException(final Throwable cause) {
        super(cause);
    }
}
