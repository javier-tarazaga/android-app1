
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.entity.UserEntity;
import com.tinygrip.android.data.entity.mapper.UserEntityDataMapper;
import com.tinygrip.android.data.repository.datasource.user.UserDataStore;
import com.tinygrip.android.data.repository.datasource.user.UserDataStoreFactory;
import com.tinygrip.android.domain.User;
import com.tinygrip.android.domain.repository.UserRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param userEntityDataMapper {@link UserEntityDataMapper}.
     */
    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory,
                              UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }

    @Override
    public Observable<User> user(String userName, String password) {
        // we always get the user performing a login from the cloud
        final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.userEntityLogin(userName, password).map(new Func1<UserEntity, User>() {
            @Override
            public User call(UserEntity userEntity) {
                return userEntityDataMapper.transform(userEntity);
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
