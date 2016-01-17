
package com.tinygrip.android.presentation.presenter;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.model.Root;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.exception.ErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.view.main.MainView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class MainPresenter extends DefaultSubscriber<Root> implements Presenter<MainView> {

    private MainView viewMainView;

    private final UseCase getRootUseCase;

    @Inject
    public MainPresenter(@Named("root") UseCase getRootUseCase) {
        this.getRootUseCase = getRootUseCase;
    }

    public void setView(@NonNull MainView view) {
        this.viewMainView = view;
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {
        this.getRootUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving the root
     */
    public void initialize() {
        this.loadRoot();
    }

    public void onActionProfileSelected() {
        this.viewMainView.viewProfile();
    }

    /**
     * Loads Root.
     */
    private void loadRoot() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getRoot();
    }

    private void showViewLoading() {
        this.viewMainView.showLoading();
    }

    private void hideViewLoading() {
        this.viewMainView.hideLoading();
    }

    private void showViewRetry() {
        this.viewMainView.showRetry();
    }

    private void hideViewRetry() {
        this.viewMainView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewMainView.getContext(),
                                                         errorBundle.getException());
        this.viewMainView.showError(errorMessage);
    }

    private void getRoot() {
        this.getRootUseCase.execute(new RootSubscriber());
    }

    private final class RootSubscriber extends DefaultSubscriber<Root> {

        @Override
        public void onCompleted() {
            MainPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);

            MainPresenter.this.hideViewLoading();
            MainPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            MainPresenter.this.showViewRetry();
        }
    }
}
