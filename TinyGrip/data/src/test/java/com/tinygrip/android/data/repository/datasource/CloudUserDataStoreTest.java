
package com.tinygrip.android.data.repository.datasource;

import com.tinygrip.android.data.ApplicationTestCase;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.api.root.RootRestApi;

import com.tinygrip.android.data.repository.datasource.user.CloudUserDataStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CloudUserDataStoreTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 765;

  private CloudUserDataStore cloudUserDataStore;

  @Mock private RootRestApi mockRootRestApi;
  @Mock private UserCache mockUserCache;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    cloudUserDataStore = new CloudUserDataStore(mockRootRestApi, mockUserCache, this.diskUserCache);
  }

  @Test
  public void testGetUserEntityListFromApi() {
    cloudUserDataStore.userEntityList();
    verify(mockRootRestApi).userEntityList();
  }

  @Test
  public void testGetUserEntityDetailsFromApi() {
    UserEntity fakeUserEntity = new UserEntity();
    Observable<UserEntity> fakeObservable = Observable.just(fakeUserEntity);
    given(mockRootRestApi.userEntityById(FAKE_USER_ID)).willReturn(fakeObservable);

    cloudUserDataStore.userEntityDetails(FAKE_USER_ID);

    verify(mockRootRestApi).userEntityById(FAKE_USER_ID);
  }
}
