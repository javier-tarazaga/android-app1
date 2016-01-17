
package com.tinygrip.android.domain;

import com.tinygrip.android.domain.model.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTest {

  private static final String FAKE_USER_ID = "8";

  private User user;

  @Before
  public void setUp() {
    user = new User(FAKE_USER_ID);
  }

  @Test
  public void testUserConstructorHappyCase() {
    String userId = user.getId();

    assertThat(userId, is(FAKE_USER_ID));
  }
}
