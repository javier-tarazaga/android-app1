
package com.tinygrip.android.presentation.view.area;

import android.support.annotation.NonNull;
import com.tinygrip.android.domain.exception.DefaultErrorBundle;
import com.tinygrip.android.domain.interactor.DefaultSubscriber;
import com.tinygrip.android.domain.interactor.area.GetArea;
import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.presentation.exception.ErrorMessageFactory;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.model.area.PreviewAreaModel;
import com.tinygrip.android.presentation.presenter.Presenter;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@ActivityScope
public class AreaPresenter implements Presenter<AreaView> {

    private final GetArea getArea;

    private AreaView areaView;
    private PreviewAreaModel previewArea;

    //private final UseCase getUserDetailsUseCase;
    //private final UserModelDataMapper userModelDataMapper;

    //@Inject
    //public NewAreaStep1Presenter(@Named("userDetails") UseCase getUserDetailsUseCase,
    //    UserModelDataMapper userModelDataMapper) {
    //  this.getUserDetailsUseCase = getUserDetailsUseCase;
    //  this.userModelDataMapper = userModelDataMapper;
    //}

    @Inject
    public AreaPresenter(GetArea getArea) {
        this.getArea = getArea;
    }

    public void setView(@NonNull AreaView view) {
        this.areaView = view;
    }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {
        this.getArea.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving area details
     */
    public void initialize(PreviewAreaModel previewArea) {
        this.previewArea = previewArea;

        this.loadArea();
    }

    public void onBackClicked() {
        this.areaView.goBack();
    }

    private void loadArea() {
        this.showViewLoading();
        this.hideViewRetry();
        this.getArea.init(this.previewArea.getPreviewArea()).execute(new GetAreaSubscriber());
    }

    private void showAreaDetailsInView(Area area) {
        this.hideViewLoading();
        this.hideViewRetry();
        this.areaView.renderArea(area);
    }

    private void showViewRetry() {
        this.areaView.showRetry();
    }

    private void hideViewRetry() {
        this.areaView.hideRetry();
    }

    private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
        String message = ErrorMessageFactory.create(this.areaView.getContext(), defaultErrorBundle.getException());
        this.areaView.showError(message);
    }

    private void showViewLoading() {
        this.areaView.showLoading();
    }

    private void hideViewLoading() {
        this.areaView.hideLoading();
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ////// SUBSCRIBERS ////////
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final class GetAreaSubscriber extends DefaultSubscriber<Area> {

        @Override
        public void onCompleted() {
            AreaPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);

            AreaPresenter.this.hideViewLoading();
            AreaPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            AreaPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Area area) {
            super.onNext(area);

            AreaPresenter.this.hideViewLoading();
            AreaPresenter.this.showAreaDetailsInView(area);
        }
    }
}
