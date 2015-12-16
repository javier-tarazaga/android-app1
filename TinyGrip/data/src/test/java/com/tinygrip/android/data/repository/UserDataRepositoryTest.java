
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.ApplicationTestCase;
import com.tinygrip.android.data.entity.mapper.UserEntityDataMapper;
import com.tinygrip.android.data.repository.datasource.user.UserDataStore;
import com.tinygrip.android.data.repository.datasource.user.UserDataStoreFactory;
import com.tinygrip.android.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

public class UserDataRepositoryTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 123;

  private UserDataRepository userDataRepository;

  @Mock private UserDataStoreFactory mockUserDataStoreFactory;
  @Mock private UserEntityDataMapper mockUserEntityDataMapper;
  @Mock private UserDataStore mockUserDataStore;
  @Mock private UserEntity mockUserEntity;
  @Mock private User mockUser;

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    userDataRepository = new UserDataRepository(mockUserDataStoreFactory,
        mockUserEntityDataMapper);

    given(mockUserDataStoreFactory.create(anyInt())).willReturn(mockUserDataStore);
    given(mockUserDataStoreFactory.createCloudDataStore()).willReturn(mockUserDataStore);
  }

  @Test
  public void testGetUsersHappyCase() {
    List<UserEntity> usersList = new ArrayList<>();
    usersList.add(new UserEntity());
    given(mockUserDataStore.userEntityList()).willReturn(Observable.just(usersList));

    userDataRepository.users();

    verify(mockUserDataStoreFactory).createCloudDataStore();
    verify(mockUserDataStore).userEntityList();
  }

  @Test
  public void testGetUserHappyCase() {
    UserEntity userEntity = new UserEntity();
    given(mockUserDataStore.userEntityDetails(FAKE_USER_ID)).willReturn(Observable.just(userEntity));
    userDataRepository.user(FAKE_USER_ID);

    verify(mockUserDataStoreFactory).create(FAKE_USER_ID);
    verify(mockUserDataStore).userEntityDetails(FAKE_USER_ID);
  }
}
