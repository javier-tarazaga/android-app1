
package com.tinygrip.android.test.mapper;

import com.tinygrip.android.presentation.view.user.mapper.UserModelDataMapper;
import com.tinygrip.android.presentation.model.UserModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class UserModelDataMapperTest extends TestCase {

  private static final int FAKE_USER_ID = 123;
  private static final String FAKE_FULLNAME = "Tony Stark";

  private UserModelDataMapper userModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    userModelDataMapper = new UserModelDataMapper();
  }

  public void testTransformUser() {
    com.tinygrip.android.domain.User user = createFakeUser();
    UserModel userModel = userModelDataMapper.transform(user);

    assertThat(userModel, is(instanceOf(UserModel.class)));
    assertThat(userModel.getUserId(), is(FAKE_USER_ID));
    assertThat(userModel.getFullName(), is(FAKE_FULLNAME));
  }

  public void testTransformUserCollection() {
    com.tinygrip.android.domain.User mockUserOne = mock(com.tinygrip.android.domain.User.class);
    com.tinygrip.android.domain.User mockUserTwo = mock(com.tinygrip.android.domain.User.class);

    List<com.tinygrip.android.domain.User> userList = new ArrayList<com.tinygrip.android.domain.User>(5);
    userList.add(mockUserOne);
    userList.add(mockUserTwo);

    Collection<UserModel> userModelList = userModelDataMapper.transform(userList);

    assertThat(userModelList.toArray()[0], is(instanceOf(UserModel.class)));
    assertThat(userModelList.toArray()[1], is(instanceOf(UserModel.class)));
    assertThat(userModelList.size(), is(2));
  }

  private com.tinygrip.android.domain.User createFakeUser() {
    com.tinygrip.android.domain.User user = new com.tinygrip.android.domain.User(FAKE_USER_ID);
    user.setFullName(FAKE_FULLNAME);

    return user;
  }
}
