package com.springboot.project.model;

import com.couchbase.client.java.json.JsonObject;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document("json_schemas")
public class JsonSchemaValidator {

    @Id
    @NotNull
    private UUID id;
    @NotNull(message = "name of schema can't be null")
    private String name;
    private Long version;
    private String status;
    @NotNull(message = "value of schema can't be null")
    private JsonObject value;

}
