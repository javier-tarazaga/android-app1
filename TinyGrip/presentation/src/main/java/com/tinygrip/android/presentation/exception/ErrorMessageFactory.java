
package com.tinygrip.android.presentation.exception;

import android.content.Context;
import com.tinygrip.android.data.exception.NetworkConnectionException;
import com.tinygrip.android.data.exception.user.UserNotFoundException;
import com.tinygrip.android.R;
import com.tinygrip.android.domain.exception.user.InvalidEmailException;
import com.tinygrip.android.domain.exception.user.InvalidLoginPasswordException;
import com.tinygrip.android.domain.exception.user.InvalidRegisterConfirmPasswordException;
import com.tinygrip.android.domain.exception.user.InvalidRegisterPasswordException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

  private ErrorMessageFactory() {
    //empty
  }

  /**
   * Creates a String representing an error message.
   *
   * @param context Context needed to retrieve string resources.
   * @param exception An exception used as a condition to retrieve the correct error message.
   * @return {@link String} an error message.
   */
  public static String create(Context context, Exception exception) {
    String message = context.getString(R.string.exception_message_generic);

    if (exception instanceof NetworkConnectionException) {
      message = context.getString(R.string.exception_message_no_connection);
    } else if (exception instanceof UserNotFoundException) {
      message = context.getString(R.string.exception_message_user_not_found);
    } else if (exception instanceof InvalidEmailException) {
      message = context.getString(R.string.exception_message_login_invalid_email);
    } else if (exception instanceof InvalidLoginPasswordException) {
      message = context.getString(R.string.exception_message_login_invalid_password);
    } else if (exception instanceof InvalidRegisterPasswordException) {
      message = context.getString(R.string.exception_message_register_invalid_password);
    } else if (exception instanceof InvalidRegisterConfirmPasswordException) {
      message = context.getString(R.string.exception_message_register_invalid_confirm_password);
    }

    return message;
  }
}
