
package com.tinygrip.android.presentation.presenter.user;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.model.user.User;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.exception.user.InvalidEmailException;
import com.tinygrip.android.domain.exception.user.InvalidRegisterConfirmPasswordException;
import com.tinygrip.android.domain.exception.user.InvalidRegisterPasswordException;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.user.UserRegister;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.user.register.UserRegisterView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class UserRegisterPresenter implements Presenter<UserRegisterView> {

    private UserRegisterView userRegisterView;

    private final UserRegister userLoginUseCase;

    @Inject
    public UserRegisterPresenter(@Named("userRegister") UserRegister userRegisterUseCase) {
        this.userLoginUseCase = userRegisterUseCase;
    }

    public void setView(@NonNull UserRegisterView view) {
        this.userRegisterView = view;
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
        this.userRegisterView.showLoading();
    }

    private void hideViewLoading() {
        this.userRegisterView.hideLoading();
    }

    private void showInvalidEmail(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.userRegisterView.getContext(),
                                                         errorBundle.getException());
        this.userRegisterView.showInvalidEmail(errorMessage);
    }

    private void showInvalidPassword(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.userRegisterView.getContext(),
                                                         errorBundle.getException());
        this.userRegisterView.showInvalidPassword(errorMessage);
    }

    private void showInvalidConfirmPassword(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.userRegisterView.getContext(),
                                                         errorBundle.getException());
        this.userRegisterView.showInvalidConfirmPassword(errorMessage);
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.userRegisterView.getContext(),
                                                         errorBundle.getException());
        this.userRegisterView.showError(errorMessage);
    }

    private void showRegisterSuccessful() {
        this.userRegisterView.showRegisterSuccessful();
    }

    /**
     * Perform a login. This process will include a simple validation in order to avoid making innecesary
     * calls to the backend.
     *  @param userName The user name to perform the registration with
     * @param password The password to perform the registration with
     * @param confirmPassword The password confirmation to verify the registration
     */
    public void registerUser(String userName, String password, String confirmPassword) {
        this.showViewLoading();
        userLoginUseCase.initialize(userName, password, confirmPassword);
        userLoginUseCase.execute(new UserRegisterSubscriber());
    }

    public void onUpClicked() {
        this.userRegisterView.goUp();
    }

    private final class UserRegisterSubscriber extends DefaultSubscriber<User> {

        @Override
        public void onCompleted() {
            UserRegisterPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserRegisterPresenter.this.hideViewLoading();

            if (e instanceof InvalidEmailException) {
                UserRegisterPresenter.this.showInvalidEmail(new DefaultErrorBundle((Exception) e));
            } else if (e instanceof InvalidRegisterPasswordException) {
                UserRegisterPresenter.this.showInvalidPassword(new DefaultErrorBundle((Exception) e));
            } else if (e instanceof InvalidRegisterConfirmPasswordException) {
                UserRegisterPresenter.this.showInvalidConfirmPassword(new DefaultErrorBundle((Exception) e));
            } else {
                UserRegisterPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            }
        }

        @Override
        public void onNext(User user) {
            UserRegisterPresenter.this.showRegisterSuccessful();
        }
    }
}
