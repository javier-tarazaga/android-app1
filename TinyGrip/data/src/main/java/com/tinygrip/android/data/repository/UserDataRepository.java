/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tinygrip.android.data.repository;

import com.tinygrip.android.data.entity.UserEntity;
import com.tinygrip.android.data.entity.mapper.UserEntityDataMapper;
import com.tinygrip.android.data.repository.datasource.UserDataStore;
import com.tinygrip.android.data.repository.datasource.UserDataStoreFactory;
import com.tinygrip.android.domain.repository.UserRepository;

import java.util.List;
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

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<List<com.tinygrip.android.domain.User>> users() {
    //we always get all users from the cloud
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
    return userDataStore.userEntityList().map(new Func1<List<UserEntity>, List<com.tinygrip.android.domain.User>>() {
      @Override
      public List<com.tinygrip.android.domain.User> call(List<UserEntity> userEntities) {
        return userEntityDataMapper.transform(userEntities);
      }
    });
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<com.tinygrip.android.domain.User> user(int userId) {
    final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
    return userDataStore.userEntityDetails(userId).map(new Func1<UserEntity, com.tinygrip.android.domain.User>() {
      @Override
      public com.tinygrip.android.domain.User call(UserEntity userEntity) {
        return userEntityDataMapper.transform(userEntity);
      }
    });
  }
}
