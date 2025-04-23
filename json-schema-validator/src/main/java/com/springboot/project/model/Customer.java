package com.springboot.project.model;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("customers")
public class Customer {

    @Id private UUID id;
    @NotNull private String fullName;
    @NotNull private String email;
    private org.bson.Document data;
}
