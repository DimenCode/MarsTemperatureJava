package fr.dimenspace.marstemperature;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// JUST DON'T TOUCH OK ?

public class Json {
    private  static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();

        return defaultObjectMapper;
    }
    public static JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }
}
