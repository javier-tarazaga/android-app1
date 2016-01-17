
package com.tinygrip.android.presentation.view.user.login;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.model.user.User;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.exception.user.InvalidEmailException;
import com.tinygrip.android.domain.exception.user.InvalidLoginPasswordException;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.user.UserLogin;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.model.UserModel;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.user.mapper.UserModelDataMapper;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class UserLoginPresenter implements Presenter<UserLoginView> {

    private UserLoginView userLoginView;

    private final UserLogin userLoginUseCase;
    private final UserModelDataMapper userModelDataMapper;

    @Inject
    public UserLoginPresenter(@Named("userLogin") UserLogin userLoginUseCase,
                              UserModelDataMapper userModelDataMapper) {
        this.userLoginUseCase = userLoginUseCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull UserLoginView view) {
        this.userLoginView = view;
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {
        this.userLoginUseCase.unsubscribe();
    }

    private void showViewLoading() {
        this.userLoginView.showLoading();
    }

    private void hideViewLoading() {
        this.userLoginView.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.userLoginView.getContext(),
                                                         errorBundle.getException());
        this.userLoginView.showError(errorMessage);
    }

    private void showInvalidEmail(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.userLoginView.getContext(),
                                                         errorBundle.getException());
        this.userLoginView.showInvalidEmail(errorMessage);
    }

    private void showInvalidPassword(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.userLoginView.getContext(),
                                                         errorBundle.getException());
        this.userLoginView.showInvalidPassword(errorMessage);
    }

    private void showUserDetailsInView(User user) {
        final UserModel userModel = this.userModelDataMapper.transform(user);
        this.userLoginView.loginSuccessful(userModel);
    }

    /**
     * Perform a login. This process will include a simple validation in order to avoid making innecesary
     * calls to the backend.
     *
     * @param userName The user name to perform the login with
     * @param password The password to perform the login with
     */
    public void login(String userName, String password) {
        this.showViewLoading();

        userLoginUseCase.initialize(userName, password)
                        .execute(new UserLoginSubscriber());
    }

    public void onUpClicked() {
        this.userLoginView.goUp();
    }

    private final class UserLoginSubscriber extends DefaultSubscriber<User> {

        @Override
        public void onCompleted() {
            UserLoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserLoginPresenter.this.hideViewLoading();
            if (e instanceof InvalidEmailException) {
                UserLoginPresenter.this.showInvalidEmail(new DefaultErrorBundle((Exception) e));
            } else if (e instanceof InvalidLoginPasswordException) {
                UserLoginPresenter.this.showInvalidPassword(new DefaultErrorBundle((Exception) e));
            } else {
                UserLoginPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            }
        }

        @Override
        public void onNext(User user) {
            UserLoginPresenter.this.showUserDetailsInView(user);
        }
    }
}
