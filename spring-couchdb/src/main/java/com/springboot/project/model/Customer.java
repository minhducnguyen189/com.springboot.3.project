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
@Document("customers")
public class Customer {

    @Id
    private UUID id;
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    private JsonObject data;

}
