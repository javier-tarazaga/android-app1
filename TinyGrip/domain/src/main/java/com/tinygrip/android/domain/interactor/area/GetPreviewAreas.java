
package com.tinygrip.android.domain.interactor.area;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.model.area.PreviewArea;
import com.tinygrip.android.domain.repository.AreaRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link PreviewArea}.
 */
public class GetPreviewAreas extends UseCase {

    private final AreaRepository areaRepository;

    @Inject
    public GetPreviewAreas(AreaRepository areaRepository,
                           ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.areaRepository = areaRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.areaRepository.previewAreas();
    }
}
