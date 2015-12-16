
package com.tinygrip.android.data.exception;

/**
 * Exception throw by the application when a there is a network connection exception.
 */
public class NoNetworkConnectionException extends Exception {

  public NoNetworkConnectionException() {
    super();
  }

  public NoNetworkConnectionException(final String message) {
    super(message);
  }

  public NoNetworkConnectionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public NoNetworkConnectionException(final Throwable cause) {
    super(cause);
  }
}
