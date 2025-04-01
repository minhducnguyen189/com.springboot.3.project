
package com.springboot.project.model;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@org.springframework.data.mongodb.core.mapping.Document("database_sequence")
public class DatabaseSequence {

    @Id
    @NotNull
    private String id;
    @Min(value = 1, message = "version must be started from 1")
    private Long version;

}