package com.tinygrip.android.domain.exception.user;

/**
 * Exception thrown when the password used for login is not valid
 **/
public class InvalidLoginPasswordException extends Exception {

    public InvalidLoginPasswordException() {
    }

    public InvalidLoginPasswordException(String message) {
        super(message);
    }

    public InvalidLoginPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLoginPasswordException(Throwable cause) {
        super(cause);
    }

    public InvalidLoginPasswordException(String message,
                                         Throwable cause,
                                         boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
