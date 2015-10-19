
package com.tinygrip.android.data.repository.datasource;

import com.tinygrip.android.data.ApplicationTestCase;
import com.tinygrip.android.data.cache.user.UserCache;
import com.tinygrip.android.data.repository.datasource.user.DiskUserDataStore;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DiskUserDataStoreTest extends ApplicationTestCase {

  private static final int FAKE_USER_ID = 11;

  private DiskUserDataStore diskUserDataStore;

  @Mock private UserCache mockUserCache;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    diskUserDataStore = new DiskUserDataStore(mockUserCache);
  }

  @Test
  public void testGetUserEntityListUnsupported() {
    expectedException.expect(UnsupportedOperationException.class);
    diskUserDataStore.userEntityList();
  }

  @Test
  public void testGetUserEntityDetailesFromCache() {
    diskUserDataStore.userEntityDetails(FAKE_USER_ID);
    verify(mockUserCache).get(FAKE_USER_ID);
  }
}
