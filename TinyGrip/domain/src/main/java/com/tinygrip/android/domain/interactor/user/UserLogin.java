
package com.tinygrip.android.domain.interactor.user;

import com.tinygrip.android.domain.exception.user.InvalidEmailException;
import com.tinygrip.android.domain.exception.user.InvalidLoginPasswordException;
import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.model.user.User;
import com.tinygrip.android.domain.repository.UserRepository;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * This class is an implementation of {@link com.tinygrip.android.domain.interactor.UseCase} that represents a use case for
 * retrieving data related to an specific {@link User} performing
 * a login.
 */
public class UserLogin extends UseCase {

    private final UserRepository userRepository;

    private String email;
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
        if (this.email == null || this.password == null) {
            throw new RuntimeException("Neither email or password can be null!");
        }

        return Observable.concat(validate(), this.userRepository.user(this.email, this.password));
    }

    /**
     * Simple case initializer in which we setup both the email and password. This process cannot be done
     * in the @Inject due to the fact that when we init the module we still won't know this params.
     *  @param email The user email to login with
     * @param password The password to login with
     */
    public UserLogin initialize(String email, String password) {
        this.email = email;
        this.password = password;

        return this;
    }

    private Observable validate() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                if (UserLogin.this.email.isEmpty()) {
                    subscriber.onError(new InvalidEmailException());
                } else if (UserLogin.this.password.isEmpty()) {
                    subscriber.onError(new InvalidLoginPasswordException());
                } else {
                    subscriber.onCompleted();
                }
            }
        });
    }
}
