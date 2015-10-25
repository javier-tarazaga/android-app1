
package com.tinygrip.android.data.cache.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * A Jackson implementation of a {@link @JsonSerializer}
 */
public class JacksonJsonSerializer implements JsonSerializer {

  private final ObjectMapper objectMapper;

  public JacksonJsonSerializer(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Serialize an object to Json.
   *
   * @param object {@link Object} to serialize.
   */
  @Override
  public String serialize(Object object) throws JsonProcessingException {
    return this.objectMapper.writeValueAsString(object);
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param jsonString A json string to deserialize.
   * @return The deserialized object
   */
  @Override
  public <T> T deserialize(String jsonString, Class<T> typeValue) throws IOException {
    return this.objectMapper.readValue(jsonString, typeValue);
  }
}
