
package com.tinygrip.android.test.mapper;

import com.tinygrip.android.domain.model.User;
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

    private static final String FAKE_USER_ID = "123";
    private static final String FAKE_FIRST_NAME = "Tony";
    private static final String FAKE_LAST_NAME = "Stark";

    private UserModelDataMapper userModelDataMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userModelDataMapper = new UserModelDataMapper();
    }

    public void testTransformUser() {
        User user = createFakeUser();
        UserModel userModel = userModelDataMapper.transform(user);

        assertThat(userModel, is(instanceOf(UserModel.class)));
        assertThat(userModel.getId(), is(FAKE_USER_ID));
        assertThat(userModel.getFirstName(), is(FAKE_FIRST_NAME));
        assertThat(userModel.getLastName(), is(FAKE_LAST_NAME));
    }

    public void testTransformUserCollection() {
        User mockUserOne = mock(User.class);
        User mockUserTwo = mock(User.class);

        List<User> userList = new ArrayList<User>(5);
        userList.add(mockUserOne);
        userList.add(mockUserTwo);

        Collection<UserModel> userModelList = userModelDataMapper.transform(userList);

        assertThat(userModelList.toArray()[0], is(instanceOf(UserModel.class)));
        assertThat(userModelList.toArray()[1], is(instanceOf(UserModel.class)));
        assertThat(userModelList.size(), is(2));
    }

    private User createFakeUser() {
        User user = new User(FAKE_USER_ID);
        user.setFirstName(FAKE_FIRST_NAME);
        user.setLastName(FAKE_LAST_NAME);

        return user;
    }
}
