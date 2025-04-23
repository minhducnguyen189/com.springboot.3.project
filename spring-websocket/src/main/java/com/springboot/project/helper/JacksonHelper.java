package com.springboot.project.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JacksonHelper {
    public <T> T toObject(String payload, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(payload, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T toObject(JsonNode jsonNode, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.treeToValue(jsonNode, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode toJsonNode(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        try {
            return objectMapper.valueToTree(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
