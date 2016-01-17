
package com.tinygrip.android.presentation.view.area;

import com.tinygrip.android.presentation.internal.di.ActivityScope;
import com.tinygrip.android.presentation.model.UserModel;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Area} (in the domain layer) to {@link UserModel} in the
 * presentation layer.
 */
@ActivityScope
public class AreaModelDataMapper {

  @Inject
  public AreaModelDataMapper() {}

  ///**
  // * Transform a {@link User} into an {@link UserModel}.
  // *
  // * @param user Object to be transformed.
  // * @return {@link UserModel}.
  // */
  //public UserModel transform(User user) {
  //  if (user == null) {
  //    throw new IllegalArgumentException("Cannot transform a null value");
  //  }
  //  UserModel userModel = new UserModel(user.getId());
  //  userModel.setCoverUrl(user.getCoverUrl());
  //  userModel.setFullName(user.getFullName());
  //  userModel.setEmail(user.getEmail());
  //  userModel.setDescription(user.getDescription());
  //  userModel.setFollowers(user.getFollowers());
  //
  //  return userModel;
  //}

  ///**
  // * Transform a Collection of {@link User} into a Collection of {@link UserModel}.
  // *
  // * @param usersCollection Objects to be transformed.
  // * @return List of {@link UserModel}.
  // */
  //public Collection<UserModel> transform(Collection<User> usersCollection) {
  //  Collection<UserModel> userModelsCollection;
  //
  //  if (usersCollection != null && !usersCollection.isEmpty()) {
  //    userModelsCollection = new ArrayList<>();
  //    for (User user : usersCollection) {
  //      userModelsCollection.add(transform(user));
  //    }
  //  } else {
  //    userModelsCollection = Collections.emptyList();
  //  }
  //
  //  return userModelsCollection;
  //}
}
