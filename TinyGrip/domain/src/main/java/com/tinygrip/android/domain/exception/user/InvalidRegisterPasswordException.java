package com.tinygrip.android.domain.exception.user;

/**
 * Exception thrown when an password does not conform to the following rules:
 *
 * <b>RequiredLength =</b> 6
 * <b>RequireDigit =</b> true
 * <b>RequireLowercase =</b> true
 * <b>RequireUppercase =</b> true
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
