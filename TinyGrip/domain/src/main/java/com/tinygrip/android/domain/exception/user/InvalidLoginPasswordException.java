package com.tinygrip.android.domain.exception.user;

/**
 * Exception thrown when an password does not conform to the following rules:
 *
 * <b>RequiredLength =</b> 6
 * <b>RequireDigit =</b> true
 * <b>RequireLowercase =</b> true
 * <b>RequireUppercase =</b> true
 */
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
