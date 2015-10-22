
package com.tinygrip.android.test.presenter;

import android.content.Context;
import android.test.AndroidTestCase;
import com.tinygrip.android.domain.interactor.user.UserLogin;
import com.tinygrip.android.presentation.presenter.user.UserLoginPresenter;
import com.tinygrip.android.presentation.view.user.mapper.UserModelDataMapper;
import com.tinygrip.android.presentation.view.user.view.UserLoginView;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class UserLoginPresenterTest extends AndroidTestCase {

    private static final String FAKE_USER_NAME = "fake";
    private static final String FAKE_PASSWORD = "fake";

    private UserLoginPresenter userLoginPresenter;

    @Mock
    private Context mockContext;

    @Mock
    private UserLoginView mockUserLoginView;

    @Mock
    private UserLogin mockUserLogin;

    @Mock
    private UserModelDataMapper mockUserModelDataMapper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        userLoginPresenter = new UserLoginPresenter(mockUserLogin, mockUserModelDataMapper);
        userLoginPresenter.setView(mockUserLoginView);
    }

    public void testUserLoginPresenterLogin() {
        given(mockUserLoginView.getContext()).willReturn(mockContext);

        userLoginPresenter.login(FAKE_USER_NAME, FAKE_PASSWORD);

        verify(mockUserLoginView).showLoading();
        verify(mockUserLogin).execute(any(Subscriber.class));
    }
}
