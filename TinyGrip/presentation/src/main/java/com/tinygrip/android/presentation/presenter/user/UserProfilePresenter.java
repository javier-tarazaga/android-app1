package com.tinygrip.android.presentation.presenter.user;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.model.Root;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.presenter.Presenter;
import com.tinygrip.android.presentation.view.user.profile.UserProfileView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class UserProfilePresenter extends DefaultSubscriber<Root> implements Presenter<UserProfileView> {

    private UserProfileView viewUserProfile;

    private final UseCase getUserIsAuthUseCase;

    @Inject
    public UserProfilePresenter(@Named("userIsValid") UseCase getUserIsAuthUseCase) {
        this.getUserIsAuthUseCase = getUserIsAuthUseCase;
    }

    public void setView(@NonNull UserProfileView view) {
        this.viewUserProfile = view;
    }

    @Override
    public void resume() {
        this.loadIfValidUser();
    }

    @Override
    public void pause() {}

    @Override
    public void destroy() {
        this.getUserIsAuthUseCase.unsubscribe();
    }

    /**
     * Loads Root.
     */
    private void loadIfValidUser() {
        this.showViewLoading();
        this.getIfValidUser();
    }

    private void showViewLoading() {
        this.viewUserProfile.showLoading();
    }

    private void hideViewLoading() {
        this.viewUserProfile.hideLoading();
    }

    private void showUserLoginView() {
        this.viewUserProfile.showUserLoginView();
    }

    private void showUserDetailsView() {
        this.viewUserProfile.showUserDetailsView();
    }


    public void onLoginSuccessful() {
        this.viewUserProfile.showUserDetailsView();
    }

    public void onLogoutSuccessful() {
        this.viewUserProfile.showUserLoginView();
    }

    private void getIfValidUser() {
        this.getUserIsAuthUseCase.execute(new IsValidUserSubscriber());
    }

    private final class IsValidUserSubscriber extends DefaultSubscriber<Boolean> {

        @Override
        public void onCompleted() {
            UserProfilePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            UserProfilePresenter.this.hideViewLoading();
            UserProfilePresenter.this.showUserLoginView();
        }

        @Override
        public void onNext(Boolean isValid) {
            if (isValid) {
                UserProfilePresenter.this.showUserDetailsView();
            } else {
                UserProfilePresenter.this.showUserLoginView();
            }
        }
    }
}
