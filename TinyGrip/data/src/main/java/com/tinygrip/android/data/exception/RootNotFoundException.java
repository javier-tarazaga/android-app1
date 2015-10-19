
package com.tinygrip.android.data.exception;

/**
 * Exception throw by the application when a Root search can't return a valid result.
 */
public class RootNotFoundException extends Exception {

  public RootNotFoundException() {
    super();
  }

  public RootNotFoundException(final String message) {
    super(message);
  }

  public RootNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public RootNotFoundException(final Throwable cause) {
    super(cause);
  }
}
