
package com.tinygrip.android.domain.interactor.user;

import com.tinygrip.android.domain.User;
import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.repository.UserRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for retrieving data related to an specific
 * {@link User} performing a login.
 */
public class UserRegister extends UseCase {

    private final UserRepository userRepository;

    private String email;
    private String password;
    private String confirmPassword;

    @Inject
    public UserRegister(UserRepository userRepository,
                        ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        if (this.email == null || this.password == null || this.confirmPassword == null) {
            throw new RuntimeException("Neither userName or password can be null!");
        }

        return this.userRepository.registerUser(this.email, this.password, this.confirmPassword);
    }

    /**
     * Simple case initializer in which we setup both the email, password and confirmation password. This process cannot
     * be done in the @Inject due to the fact that when we init the module we still won't know this params.
     *
     * @param email The user name to register with
     * @param password The password to register with
     * @param confirmPassword The password confirmation (should match the password)
     */
    public void initialize(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
