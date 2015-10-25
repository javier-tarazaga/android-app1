
package com.tinygrip.android.data.exception.user;

/**
 * Exception throw by the application when a the auth token has expired or is not valid
 */
public class InvalidAuthToken extends Exception {

  public InvalidAuthToken() {
    super();
  }

  public InvalidAuthToken(final String message) {
    super(message);
  }

  public InvalidAuthToken(final String message, final Throwable cause) {
    super(message, cause);
  }

  public InvalidAuthToken(final Throwable cause) {
    super(cause);
  }
}
