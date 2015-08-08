/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tinygrip.android.data.entity.mapper;

import com.tinygrip.android.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link com.tinygrip.android.domain.User} in the
 * domain layer.
 */
@Singleton
public class UserEntityDataMapper {

  @Inject
  public UserEntityDataMapper() {}

  /**
   * Transform a {@link UserEntity} into an {@link com.tinygrip.android.domain.User}.
   *
   * @param userEntity Object to be transformed.
   * @return {@link com.tinygrip.android.domain.User} if valid {@link UserEntity} otherwise null.
   */
  public com.tinygrip.android.domain.User transform(UserEntity userEntity) {
    com.tinygrip.android.domain.User user = null;
    if (userEntity != null) {
      user = new com.tinygrip.android.domain.User(userEntity.getUserId());
      user.setCoverUrl(userEntity.getCoverUrl());
      user.setFullName(userEntity.getFullname());
      user.setDescription(userEntity.getDescription());
      user.setFollowers(userEntity.getFollowers());
      user.setEmail(userEntity.getEmail());
    }

    return user;
  }

  /**
   * Transform a List of {@link UserEntity} into a Collection of {@link com.tinygrip.android.domain.User}.
   *
   * @param userEntityCollection Object Collection to be transformed.
   * @return {@link com.tinygrip.android.domain.User} if valid {@link UserEntity} otherwise null.
   */
  public List<com.tinygrip.android.domain.User> transform(Collection<UserEntity> userEntityCollection) {
    List<com.tinygrip.android.domain.User> userList = new ArrayList<>(20);
    com.tinygrip.android.domain.User user;
    for (UserEntity userEntity : userEntityCollection) {
      user = transform(userEntity);
      if (user != null) {
        userList.add(user);
      }
    }

    return userList;
  }
}
