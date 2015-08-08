package com.tinygrip.android.data.api.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import javax.xml.datatype.Duration;

/**
 * String converter to be used by Jackson library to convert null values in strings into empty strings instead.
 */
public final class StringConverterModule extends SimpleModule {

    public StringConverterModule() {
        addDeserializer(String.class, new StringDeserializer());
        addSerializer(String.class, new StringSerializer());
    }

    private final class StringSerializer extends StdScalarSerializer<String> {
        public StringSerializer() { super(String.class); }

        @Override
        public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws
            IOException {
            jgen.writeString(value);
        }
    }

    private static final class StringDeserializer extends StdScalarDeserializer<String> {
        public StringDeserializer() { super(Duration.class); }

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {

            return jsonParser.getText();
        }

        @Override
        public String getNullValue() {
            return "";
        }
    }
}
