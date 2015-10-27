package com.tinygrip.android.domain.exception.user;

/**
 * Exception thrown when an email does not conform to what we expect to be valid
 */
public class InvalidRegisterConfirmPasswordException extends Exception {

    public InvalidRegisterConfirmPasswordException() {
    }

    public InvalidRegisterConfirmPasswordException(String message) {
        super(message);
    }

    public InvalidRegisterConfirmPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRegisterConfirmPasswordException(Throwable cause) {
        super(cause);
    }

    public InvalidRegisterConfirmPasswordException(String message,
                                                   Throwable cause,
                                                   boolean enableSuppression,
                                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
