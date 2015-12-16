package com.tinygrip.android.domain.interactor;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.model.Root;
import com.tinygrip.android.domain.repository.RootRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving the {@link Root} from where to get all the links to
 * start the application.
 */
public class GetRoot extends UseCase {

    private final String apiKey;
    private final RootRepository rootRepository;

    @Inject
    public GetRoot(String apiKey,
                   RootRepository rootRepository,
                   ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.apiKey = apiKey;
        this.rootRepository = rootRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.rootRepository.root(apiKey);
    }
}
