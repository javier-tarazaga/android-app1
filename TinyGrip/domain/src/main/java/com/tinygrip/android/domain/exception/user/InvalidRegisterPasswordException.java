package com.tinygrip.android.domain.exception.user;

/**
 * Exception thrown when an email does not conform to what we expect to be valid
 */
public class InvalidRegisterPasswordException extends Exception {

    public InvalidRegisterPasswordException() {
    }

    public InvalidRegisterPasswordException(String message) {
        super(message);
    }

    public InvalidRegisterPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRegisterPasswordException(Throwable cause) {
        super(cause);
    }

    public InvalidRegisterPasswordException(String message,
                                            Throwable cause,
                                            boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
