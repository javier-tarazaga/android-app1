package com.tinygrip.android.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.user.activity.UserLoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class UserLoginActivityTest extends ActivityInstrumentationTestCase2<UserLoginActivity> {

    private UserLoginActivity userLoginActivity;

    public UserLoginActivityTest() {
        super(UserLoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityIntent(createTargetIntent());
        this.userLoginActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsUserLoginFragment() {
        Fragment userLoginFragment = userLoginActivity.getFragmentManager().findFragmentById(R.id.fl_fragment);
        assertThat(userLoginFragment, is(notNullValue()));
    }

    public void testContainsProperTitle() {
        String actualTitle = this.userLoginActivity.getTitle().toString().trim();

        assertThat(actualTitle, is("User Details"));
    }

    public void testLoadUserHappyCaseViews() {
        onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

        onView(withId(R.id.tv_fullname)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_email)).check(matches(isDisplayed()));
    }

    public void testLoadUserHappyCaseData() {
        onView(withId(R.id.tv_fullname)).check(matches(withText("John Sanchez")));
        onView(withId(R.id.tv_email)).check(matches(withText("dmedina@katz.edu")));
    }

    private Intent createTargetIntent() {
        return UserLoginActivity.getCallingIntent(getInstrumentation().getTargetContext());
    }
}
