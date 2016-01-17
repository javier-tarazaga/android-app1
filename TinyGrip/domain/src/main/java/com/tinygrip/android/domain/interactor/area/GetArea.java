
package com.tinygrip.android.domain.interactor.area;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.model.area.Area;
import com.tinygrip.android.domain.model.area.PreviewArea;
import com.tinygrip.android.domain.repository.AreaRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving an {@link Area}.
 */
public class GetArea extends UseCase {

    private final AreaRepository areaRepository;
    private PreviewArea previewArea;

    @Inject
    public GetArea(AreaRepository areaRepository,
                   ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.areaRepository = areaRepository;
    }

    public GetArea init(PreviewArea previewArea) {
        this.previewArea = previewArea;

        return this;
    }

    @Override
    public Observable buildUseCaseObservable() {
        if (this.previewArea == null) {
            throw new IllegalArgumentException("init(previewAre) not called, or called with null argument.");
        }

        return this.areaRepository.area(this.previewArea.getSelf().getHref());
    }
}
