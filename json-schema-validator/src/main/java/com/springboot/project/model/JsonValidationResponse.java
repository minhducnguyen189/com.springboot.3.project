package com.springboot.project.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

@Getter
@Setter
public class JsonValidationResponse {
    
    private boolean validJson;
    private Long schemaVersion;
    private Document jsonInput;

}
