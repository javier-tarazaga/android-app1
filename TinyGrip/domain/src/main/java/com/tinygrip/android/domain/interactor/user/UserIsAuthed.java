
package com.tinygrip.android.domain.interactor.user;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.repository.UserRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case to simply check if we have a valid
 * auth state of not
 */
public class UserIsAuthed extends UseCase {

    private final UserRepository userRepository;

    @Inject
    public UserIsAuthed(UserRepository userRepository,
                        ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.userRepository.isValidUser();
    }
}
