//
//package com.tinygrip.android.domain.interactor;
//
//import com.tinygrip.android.domain.repository.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.verifyZeroInteractions;
//
//public class GetUserListTest {
//
//  private GetUserList getUserList;
//
//  @Mock private com.tinygrip.android.domain.executor.ThreadExecutor mockThreadExecutor;
//  @Mock private com.tinygrip.android.domain.executor.PostExecutionThread mockPostExecutionThread;
//  @Mock private UserRepository mockUserRepository;
//
//  @Before
//  public void setUp() {
//    MockitoAnnotations.initMocks(this);
//    getUserList = new GetUserList(mockUserRepository, mockThreadExecutor,
//        mockPostExecutionThread);
//  }
//
//  @Test
//  public void testGetUserListUseCaseObservableHappyCase() {
//    getUserList.buildUseCaseObservable();
//
//    verify(mockUserRepository).users();
//    verifyNoMoreInteractions(mockUserRepository);
//    verifyZeroInteractions(mockThreadExecutor);
//    verifyZeroInteractions(mockPostExecutionThread);
//  }
//}
