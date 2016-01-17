
package com.tinygrip.android.domain.interactor.user;

import com.tinygrip.android.domain.model.user.User;
import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.repository.UserRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link com.tinygrip.android.domain.interactor.UseCase} that represents a use case for
 * retrieving data for the {@link User}
 */
public class UserDetails extends UseCase {

    private final UserRepository userRepository;

    @Inject
    public UserDetails(UserRepository userRepository,
                       ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.userRepository.user();
    }
}
