
package com.tinygrip.android.presentation.view.user;

import com.tinygrip.android.domain.executor.PostExecutionThread;
import com.tinygrip.android.domain.executor.ThreadExecutor;
import com.tinygrip.android.domain.interactor.UseCase;
import com.tinygrip.android.domain.interactor.user.UserDetails;
import com.tinygrip.android.domain.interactor.user.UserIsAuthed;
import com.tinygrip.android.domain.interactor.user.UserLogin;
import com.tinygrip.android.domain.interactor.user.UserLogout;
import com.tinygrip.android.domain.interactor.user.UserRegister;
import com.tinygrip.android.domain.repository.UserRepository;
import com.tinygrip.android.presentation.internal.di.ActivityScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

    public UserModule() {}

    @Provides
    @ActivityScope
    @Named("userIsValid")
    UseCase providesUserIsAuthed(UserRepository userRepository,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
        return new UserIsAuthed(userRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @ActivityScope
    @Named("userDetails")
    UseCase provideGetUser(UserRepository userRepository,
                           ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread) {
        return new UserDetails(userRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @ActivityScope
    @Named("userLogin")
    UserLogin provideGetUserByLogin(UserRepository userRepository,
                                    ThreadExecutor threadExecutor,
                                    PostExecutionThread postExecutionThread) {
        return new UserLogin(userRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @ActivityScope
    @Named("userLogout")
    UseCase providesUserLogout(UserRepository userRepository,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
        return new UserLogout(userRepository, threadExecutor, postExecutionThread);
    }

    @Provides
    @ActivityScope
    @Named("userRegister")
    UserRegister provideUserRegister(UserRepository userRepository,
                                     ThreadExecutor threadExecutor,
                                     PostExecutionThread postExecutionThread) {
        return new UserRegister(userRepository, threadExecutor, postExecutionThread);
    }
}