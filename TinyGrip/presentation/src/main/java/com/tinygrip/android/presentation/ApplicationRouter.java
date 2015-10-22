
package com.tinygrip.android.presentation;

import android.content.Context;
import android.content.Intent;
import com.tinygrip.android.presentation.view.area.activity.AreaActivity;
import com.tinygrip.android.presentation.view.area.activity.NewAreaActivity;
import com.tinygrip.android.presentation.view.user.activity.UserDetailsActivity;
import com.tinygrip.android.presentation.view.user.activity.UserLoginActivity;
import com.tinygrip.android.presentation.view.user.activity.UserRegisterActivity;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class ApplicationRouter {

    @Inject
    public void ApplicationRouter() {
        //empty
    }

    /**
     * Goes to the login screen
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToLogin(Context context) {
        if (context != null) {
            Intent intentToLaunch = UserLoginActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the user register screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToRegister(Context context) {
        if (context != null) {
            Intent intentToLaunch = UserRegisterActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the user details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToUserDetails(Context context) {
      if (context != null) {
        Intent intentToLaunch = UserDetailsActivity.getCallingIntent(context);
        context.startActivity(intentToLaunch);
      }
    }

    /**
     * Goes to the new area screen
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToNewArea(Context context) {
        if (context != null) {
            Intent intentToLaunch = NewAreaActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    /**
     * Goes to the area screen
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToArea(Context context) {
        if (context != null) {
            Intent intentToLaunch = AreaActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    //  /**
    //   * Goes to the user list screen.
    //   *
    //   * @param context A Context needed to open the destiny activity.
    //   */
    //public void navigateToUserList(Context context) {
    //  if (context != null) {
    //    Intent intentToLaunch = UserListActivity.getCallingIntent(context);
    //    context.startActivity(intentToLaunch);
    //  }
    //}
    //
}
