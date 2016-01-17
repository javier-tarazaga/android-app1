package com.tinygrip.android.presentation.view.user.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.presenter.user.UserProfilePresenter;
import com.tinygrip.android.presentation.view.base.BaseActivity;
import com.tinygrip.android.presentation.view.user.DaggerUserComponent;
import com.tinygrip.android.presentation.view.user.UserComponent;
import com.tinygrip.android.presentation.view.user.UserModule;
import com.tinygrip.android.presentation.view.user.login.UserLoginFragment;
import javax.inject.Inject;

/**
 * Activity that will show either the user profile or the login view depending on the current state of the user
 */
public class UserProfileActivity extends BaseActivity
    implements UserProfileView, HasComponent<UserComponent>, UserLoginFragment.UserLoginListener,
    UserDetailsFragment.UserDetailsListener {

    private UserComponent userComponent;

    @Inject
    UserProfilePresenter userProfilePresenter;

    @Bind(R.id.rl_progress)
    RelativeLayout relativeProgress;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        this.initializeInjector();
        this.initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.userProfilePresenter.resume();
    }

    private void initializeInjector() {
        this.userComponent = DaggerUserComponent.builder()
                                                .applicationComponent(getApplicationComponent())
                                                .activityModule(getActivityModule())
                                                .userModule(new UserModule())
                                                .build();

        this.userComponent.inject(this);
    }

    private void initialize() {
        this.userProfilePresenter.setView(this);
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
    public void logoutSuccessfully() {
        this.userProfilePresenter.onLogoutSuccessful();
    }

    @Override
    public void onRegisterLinkClicked() {
        this.applicationRouter.navigateToRegister(UserProfileActivity.this);
    }

    @Override
    public void onLoginSuccessful(UserModel userModel) {
        this.userProfilePresenter.onLoginSuccessful();
    }

    @Override
    public void showUserLoginView() {
        replaceFragment(R.id.fl_fragment_user_profile, UserLoginFragment.newInstance(), false);
    }

    @Override
    public void showUserDetailsView() {
        replaceFragment(R.id.fl_fragment_user_profile, UserDetailsFragment.newInstance(), false);
    }

    @Override
    public void showLoading() {
        relativeProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        relativeProgress.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return UserProfileActivity.this;
    }
}
