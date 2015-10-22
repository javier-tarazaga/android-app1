
package com.tinygrip.android.test.presenter;

import android.content.Context;
import android.test.AndroidTestCase;
import com.tinygrip.android.domain.interactor.user.UserRegister;
import com.tinygrip.android.presentation.presenter.user.UserRegisterPresenter;
import com.tinygrip.android.presentation.view.user.view.UserRegisterView;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Subscriber;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class UserRegisterPresenterTest extends AndroidTestCase {

    private static final String FAKE_EMAIL = "fake@gmail.com";
    private static final String FAKE_PASSWORD = "fake";
    private static final String FAKE_CONFIRM_PASSWORD = "fake";

    private UserRegisterPresenter userRegisterPresenter;

    @Mock
    private Context mockContext;

    @Mock
    private UserRegisterView mockUserRegisterView;

    @Mock
    private UserRegister mockUserRegister;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);

        userRegisterPresenter = new UserRegisterPresenter(mockUserRegister);
        userRegisterPresenter.setView(mockUserRegisterView);
    }

    public void testUserRegisterPresenterRegister() {
        given(mockUserRegisterView.getContext()).willReturn(mockContext);

        userRegisterPresenter.registerUser(FAKE_EMAIL, FAKE_PASSWORD, FAKE_CONFIRM_PASSWORD);

        verify(mockUserRegisterView).showLoading();
        verify(mockUserRegister).execute(any(Subscriber.class));
    }
}
