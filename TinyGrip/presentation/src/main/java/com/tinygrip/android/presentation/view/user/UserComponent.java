
package com.tinygrip.android.presentation.view.user;

import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.internal.di.components.ActivityComponent;
import com.tinygrip.android.presentation.internal.di.components.ApplicationComponent;
import com.tinygrip.android.presentation.internal.di.modules.ActivityModule;
import com.tinygrip.android.presentation.view.user.profile.UserProfileActivity;
import com.tinygrip.android.presentation.view.user.profile.UserDetailsFragment;
import com.tinygrip.android.presentation.view.user.login.UserLoginFragment;
import com.tinygrip.android.presentation.view.user.register.UserRegisterFragment;
import dagger.Component;

/**
 * A scope {@link ActivityScope} component.
 * Injects user specific Fragments.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class, UserModule.class })
public interface UserComponent extends ActivityComponent {
    void inject(UserProfileActivity userProfileActivity);
    void inject(UserLoginFragment userLoginFragment);
    void inject(UserRegisterFragment userRegisterFragment);
    void inject(UserDetailsFragment userDetailsFragment);
}
