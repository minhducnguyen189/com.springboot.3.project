package com.springboot.project.model;

import com.fasterxml.jackson.databind.JsonNode;

public class GenericObject {

    private final JsonNode rootNode;

    public GenericObject(JsonNode rootNode) {
        this.rootNode = rootNode;
    }

    /** Get a JsonNode using JSON Pointer syntax (e.g. /source/contract/billInfo). */
    public JsonNode getJsonProperty(String pointerPath) {
        return rootNode.at(pointerPath);
    }

    /** Get a property as a String. Returns null if path is missing or not textual. */
    public String getJsonPropertyAsString(String pointerPath) {
        JsonNode node = getJsonProperty(pointerPath);
        return node.isMissingNode() || !node.isTextual() ? null : node.asText();
    }

    /** Get a property as an Integer. Returns null if path is missing or not numeric. */
    public Integer getJsonPropertyAsInt(String pointerPath) {
        JsonNode node = getJsonProperty(pointerPath);
        return node.isMissingNode() || !node.isNumber() ? null : node.asInt();
    }

    /** Get a property as a Boolean. Returns null if path is missing or not a boolean. */
    public Boolean getJsonPropertyAsBoolean(String pointerPath) {
        JsonNode node = getJsonProperty(pointerPath);
        return node.isMissingNode() || !node.isBoolean() ? null : node.asBoolean();
    }

    /** Get a property as a Double. Returns null if path is missing or not numeric. */
    public Double getJsonPropertyAsDouble(String pointerPath) {
        JsonNode node = getJsonProperty(pointerPath);
        return node.isMissingNode() || !node.isNumber() ? null : node.asDouble();
    }
}
