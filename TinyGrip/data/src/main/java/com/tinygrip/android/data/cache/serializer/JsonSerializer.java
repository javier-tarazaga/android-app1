package com.tinygrip.android.data.cache.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
public interface JsonSerializer {

  /**
   * Serialize an object to Json.
   *
   * @param object {@link Object} to serialize.
   */
  String serialize(Object object) throws JsonProcessingException;

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return The deserialized object
   */
  <T> T deserialize(String jsonString, Class<T> typeValue) throws IOException;
}
