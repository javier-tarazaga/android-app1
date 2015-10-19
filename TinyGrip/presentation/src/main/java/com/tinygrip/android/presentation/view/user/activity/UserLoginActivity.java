package com.tinygrip.android.presentation.view.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.view.base.BaseActivity;
import com.tinygrip.android.presentation.view.user.DaggerUserComponent;
import com.tinygrip.android.presentation.view.user.UserComponent;
import com.tinygrip.android.presentation.view.user.UserModule;
import com.tinygrip.android.presentation.view.user.fragment.UserLoginFragment;

/**
 * Activity that shows details of a certain user.
 */
public class UserLoginActivity extends BaseActivity implements HasComponent<UserComponent>,UserLoginFragment.UserLoginListener {

    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserLoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        this.initializeInjector();
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                                                .applicationComponent(getApplicationComponent())
                                                .activityModule(getActivityModule())
                                                .userModule(new UserModule())
                                                .build();
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    @Override
    public void onUpClicked() {
        this.finish();
    }

    @Override
    public void onLoginSuccessful(UserModel userModel) {
        this.applicationRouter.navigateToUserDetails(UserLoginActivity.this);

        // Make sure to remove this activity from the back stack so when going back from the area activity
        // we go to the main view instead
        this.finish();
    }
}
