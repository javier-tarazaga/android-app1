
package com.tinygrip.android.domain.interactor.user;

import com.tinygrip.android.domain.model.user.User;
import com.tinygrip.android.domain.exception.user.InvalidEmailException;
import com.tinygrip.android.domain.exception.user.InvalidRegisterConfirmPasswordException;
import com.tinygrip.android.domain.exception.user.InvalidRegisterPasswordException;
import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.repository.UserRepository;
import com.tinygrip.android.domain.util.Patterns;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

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
            throw new RuntimeException("Neither email, password or confirm password can be null!");
        }

        return Observable.concat(validate(), this.userRepository.registerUser(this.email, this.password, this.confirmPassword));
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

    /**
     * Simply validate the email and password are actually correct before doing any waste of call to the backend.
     * These are the rules applied by the backend:
     *
     * RequiredLength = 6
     * RequireDigit = true - TODO - perform check
     * AllowOnlyAlphanumericUserNames = false
     * RequireUniqueEmail = true
     *
     * @return Will return true if all the fields comply with all the rules
     */
    private Observable validate() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                if (UserRegister.this.email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(UserRegister.this.email).matches()) {
                    subscriber.onError(new InvalidEmailException());
                } else if (UserRegister.this.password.isEmpty()
                    || UserRegister.this.password.length() < 6) {
                    subscriber.onError(new InvalidRegisterPasswordException());
                } else if (!UserRegister.this.password.equals(UserRegister.this.confirmPassword)) {
                    subscriber.onError(new InvalidRegisterConfirmPasswordException());
                } else {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
