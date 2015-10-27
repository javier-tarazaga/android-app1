package com.tinygrip.android.domain.exception.user;

/**
 * Exception thrown when an email does not conform to what we expect to be valid
 */
public class InvalidEmailException extends Exception {

    public InvalidEmailException() {
    }

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmailException(Throwable cause) {
        super(cause);
    }

    public InvalidEmailException(String message,
                                 Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
