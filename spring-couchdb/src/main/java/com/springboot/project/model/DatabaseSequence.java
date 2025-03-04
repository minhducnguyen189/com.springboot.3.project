
package com.springboot.project.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Getter
@Setter
@Document("database_sequence")
public class DatabaseSequence {

    @Id
    private String id;
    @Min(value = 1, message = "version must be started from 1")
    private Long version;

}