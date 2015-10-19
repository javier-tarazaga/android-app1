
package com.tinygrip.android.domain.interactor;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.repository.RootRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

public class GetRootTest {

  private static final String FAKE_API_KEY = "123";

  private GetRoot getRoot;

  @Mock private RootRepository mockRootRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    getRoot = new GetRoot(FAKE_API_KEY, mockRootRepository,
        mockThreadExecutor, mockPostExecutionThread);
  }

  @Test
  public void testGetUserDetailsUseCaseObservableHappyCase() {
    getRoot.buildUseCaseObservable();

    verify(mockRootRepository).root(FAKE_API_KEY);
    verifyNoMoreInteractions(mockRootRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }
}
