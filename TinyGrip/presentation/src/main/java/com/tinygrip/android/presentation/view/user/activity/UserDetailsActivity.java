package com.tinygrip.android.presentation.view.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.internal.di.HasComponent;
import com.tinygrip.android.presentation.view.base.BaseActivity;
import com.tinygrip.android.presentation.view.user.DaggerUserComponent;
import com.tinygrip.android.presentation.view.user.UserComponent;
import com.tinygrip.android.presentation.view.user.UserModule;
import com.tinygrip.android.presentation.view.user.fragment.UserDetailsFragment;

/**
 * Activity that shows details of a certain user.
 */
public class UserDetailsActivity extends BaseActivity implements HasComponent<UserComponent>,UserDetailsFragment.UserDetailsListener {

    private UserComponent userComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        this.initializeActivity();
        this.initializeInjector();
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity() {
        addFragment(R.id.fl_fragment, UserDetailsFragment.newInstance());
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
}
