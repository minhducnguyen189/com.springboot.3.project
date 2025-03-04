package com.springboot.project.model;

import com.couchbase.client.java.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonValidationResponse {
    
    private boolean validJson;
    private Long schemaVersion;
    private JsonObject jsonInput;

}
