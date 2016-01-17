
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.entity.mapper.UserEntityDataMapper;
import com.tinygrip.android.data.entity.user.OAuthEntity;
import com.tinygrip.android.data.entity.user.UserEntity;
import com.tinygrip.android.data.repository.datasource.auth.OAuthDataStore;
import com.tinygrip.android.data.repository.datasource.auth.OAuthDataStoreFactory;
import com.tinygrip.android.data.repository.datasource.user.UserDataStore;
import com.tinygrip.android.data.repository.datasource.user.UserDataStoreFactory;
import com.tinygrip.android.domain.model.user.User;
import com.tinygrip.android.domain.repository.UserRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final OAuthDataStoreFactory oAuthDataStoreFactory;
    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param userEntityDataMapper {@link UserEntityDataMapper}.
     */
    @Inject
    public UserDataRepository(OAuthDataStoreFactory oAuthDataStoreFactory,
                              UserDataStoreFactory dataStoreFactory,
                              UserEntityDataMapper userEntityDataMapper) {

        this.oAuthDataStoreFactory = oAuthDataStoreFactory;
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }

    @Override
    public Observable<User> user(final String userName, final String password) {
        final OAuthDataStore oAuthDataStore = this.oAuthDataStoreFactory.create();
        final UserDataStore userDataStore = this.userDataStoreFactory.create();

        return oAuthDataStore.performAuth(userName, password)
                             .flatMap(new Func1<OAuthEntity, Observable<UserEntity>>() {
                                 @Override
                                 public Observable<UserEntity> call(OAuthEntity oAuthEntity) {
                                     return userDataStore.userEntity();
                                 }
                             })
                             .map(new Func1<UserEntity, User>() {
                                 @Override
                                 public User call(UserEntity userEntity) {
                                     return userEntityDataMapper.transform(userEntity);
                                 }
                             });
    }

    @Override
    public Observable<Object> userLogout() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                UserDataRepository.this.oAuthDataStoreFactory.evictAll();
                UserDataRepository.this.userDataStoreFactory.evictAll();

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<User> user() {
        final UserDataStore userDataStore = this.userDataStoreFactory.create();
        return userDataStore.userEntity().map(new Func1<UserEntity, User>() {
            @Override
            public User call(UserEntity userEntity) {
                return userEntityDataMapper.transform(userEntity);
            }
        });
    }

    @Override
    public Observable<Boolean> isValidUser() {

        // always do this check to our current memory status for now
        final OAuthDataStore oAuthDataStore = this.oAuthDataStoreFactory.create();
        return oAuthDataStore.isAuthed();
    }

    @Override
    public Observable<User> registerUser(String email, String password, String confirmPassword) {

        // we always try registering the user performing a registration throw the cloud
        final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.userEntity().map(new Func1<UserEntity, User>() {
            @Override
            public User call(UserEntity userEntity) {
                return userEntityDataMapper.transform(userEntity);
            }
        });
    }
}
