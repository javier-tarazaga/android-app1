
package com.tinygrip.android.presentation;

import android.content.Context;
import android.content.Intent;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.view.area.AreaActivity;
import com.tinygrip.android.presentation.view.area.newArea.NewAreaActivity;
import com.tinygrip.android.presentation.view.user.login.UserLoginActivity;
import com.tinygrip.android.presentation.view.user.profile.UserProfileActivity;
import com.tinygrip.android.presentation.view.user.register.UserRegisterActivity;
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
    public void navigateToProfile(Context context) {
        if (context != null) {
            Intent intentToLaunch = UserProfileActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
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
     * @param model A {@link PreviewAreaModel} from which get all the data to display the Area
     */
    public void navigateToArea(Context context, PreviewAreaModel model) {
        if (context != null) {
            Intent intentToLaunch = AreaActivity.getCallingIntent(context, model);
            context.startActivity(intentToLaunch);
        }
    }
}
