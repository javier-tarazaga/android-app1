
package com.tinygrip.android.domain.interactor.user;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.repository.UserRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link com.tinygrip.android.domain.interactor.UseCase} that represents a use case for
 * retrieving data related to an specific {@link com.tinygrip.android.domain.User} performing
 * a login.
 */
public class UserLogin extends UseCase {

    private final UserRepository userRepository;

    private String userName;
    private String password;

    @Inject
    public UserLogin(UserRepository userRepository,
                     ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        if (this.userName == null || this.password == null) {
            throw new RuntimeException("Neither userName or password can be null!");
        }

        return this.userRepository.user(this.userName, this.password);
    }

    /**
     * Simple case initializer in which we setup both the userName and password. This process cannot be done
     * in the @Inject due to the fact that when we init the module we still won't know this params.
     *  @param userName The user name to login with
     * @param password The password to login with
     */
    public UserLogin initialize(String userName, String password) {
        this.userName = userName;
        this.password = password;

        return this;
    }
}
