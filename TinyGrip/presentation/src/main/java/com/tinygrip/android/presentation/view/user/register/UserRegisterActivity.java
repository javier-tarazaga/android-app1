package com.tinygrip.android.presentation.view.user.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.view.base.BaseActivity;
import com.tinygrip.android.presentation.view.user.DaggerUserComponent;
import com.tinygrip.android.presentation.view.user.UserComponent;
import com.tinygrip.android.presentation.view.user.UserModule;

/**
 * Activity that shows a register form to the user
 */
public class UserRegisterActivity extends BaseActivity implements HasComponent<UserComponent>,UserRegisterFragment.UserRegisterListener {

    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserRegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

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
    public void onRegisterSuccessful() {
        // Simply finish this activity for now and return to the login page to let the user login
        this.finish();
    }
}
