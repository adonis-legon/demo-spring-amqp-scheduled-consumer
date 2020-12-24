package com.example.demospringamqpscheduledconsumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
    public static String toString(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static <T> T fromString(String json, Class<T> objectType)
            throws JsonMappingException, JsonProcessingException {
        return new ObjectMapper().readValue(json, objectType);
    }
}
