
package com.tinygrip.android.data.cache.serializer;

import com.tinygrip.android.data.entity.UserEntity;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
@Singleton
public class JsonSerializer {

  private final Gson gson = new Gson();

  @Inject
  public JsonSerializer() {}

  /**
   * Serialize an object to Json.
   *
   * @param userEntity {@link UserEntity} to serialize.
   */
  public String serialize(UserEntity userEntity) {
    String jsonString = gson.toJson(userEntity, UserEntity.class);
    return jsonString;
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return {@link UserEntity}
   */
  public UserEntity deserialize(String jsonString) {
    UserEntity userEntity = gson.fromJson(jsonString, UserEntity.class);
    return userEntity;
  }
}
