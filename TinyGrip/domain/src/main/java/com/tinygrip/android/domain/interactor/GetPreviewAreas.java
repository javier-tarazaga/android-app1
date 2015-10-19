
package com.tinygrip.android.domain.interactor;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.repository.RootRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link com.tinygrip.android.domain.PreviewArea}.
 */
public class GetPreviewAreas extends UseCase {

    private final RootRepository rootRepository;

    @Inject
    public GetPreviewAreas(RootRepository rootRepository,
                           ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.rootRepository = rootRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {

        // Should never be possible to perform a login locally
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
}
