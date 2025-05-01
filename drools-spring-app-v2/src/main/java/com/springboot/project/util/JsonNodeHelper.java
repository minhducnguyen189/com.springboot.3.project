package com.springboot.project.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonNodeHelper {

    public static JsonNode getChild(JsonNode parent, String childName) {
        return parent.get(childName);
    }
}
