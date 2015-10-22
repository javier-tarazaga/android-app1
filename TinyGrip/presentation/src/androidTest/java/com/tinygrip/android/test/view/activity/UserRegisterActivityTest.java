package com.tinygrip.android.test.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;
import com.tinygrip.android.R;
import com.tinygrip.android.presentation.view.user.activity.UserRegisterActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class UserRegisterActivityTest extends ActivityInstrumentationTestCase2<UserRegisterActivity> {

    private static final String FAKE_EMAIL = "fake@gmail.com";
    private static final String FAKE_PASSWORD = "fake";
    private static final String FAKE_CONFIRM_PASSWORD = "fake";

    private UserRegisterActivity userRegisterActivity;

    public UserRegisterActivityTest() {
        super(UserRegisterActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setActivityIntent(createTargetIntent());
        this.userRegisterActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsUserRegisterFragment() {
        Fragment userRegisterFragment = userRegisterActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_user_register);
        assertThat(userRegisterFragment, is(notNullValue()));
    }

    public void testContainsProperTitle() {
        String actualTitle = this.userRegisterActivity.getTitle().toString().trim();

        assertThat(actualTitle, is("User Register"));
    }

    public void testLoadUserHappyCaseViews() {
        onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

        onView(withId(R.id.et_email)).check(matches(isDisplayed()));
        onView(withId(R.id.et_password)).check(matches(isDisplayed()));
        onView(withId(R.id.et_confirm_password)).check(matches(isDisplayed()));
    }

    public void testLoadUserHappyCaseData() {
        onView(withId(R.id.et_email)).perform(clearText(), typeText(FAKE_EMAIL));
        onView(withId(R.id.et_password)).perform(clearText(), typeText(FAKE_PASSWORD));
        onView(withId(R.id.et_confirm_password)).perform(clearText(), typeText(FAKE_CONFIRM_PASSWORD));

        onView(withId(R.id.et_email)).check(matches(withText(FAKE_EMAIL)));
        onView(withId(R.id.et_password)).check(matches(withText(FAKE_PASSWORD)));
        onView(withId(R.id.et_confirm_password)).check(matches(withText(FAKE_CONFIRM_PASSWORD)));
    }

    private Intent createTargetIntent() {
        return UserRegisterActivity.getCallingIntent(getInstrumentation().getTargetContext());
    }
}
